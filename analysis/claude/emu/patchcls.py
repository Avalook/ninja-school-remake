"""Patch copies of the original classes (never the JAR) so the emulator can:
 - load resources via the classpath (Class.getResourceAsStream -> harness.R.g)
 - run in lockstep: System.currentTimeMillis -> harness.R.now, Canvas.repaint -> harness.R.repaint"""
import struct,sys
REDIRECTS=[  # (owner,name,old_opcode) -> (new owner, name, descriptor)
 ('java/lang/Class','getResourceAsStream',0xb6,'harness/R','g','(Ljava/lang/Class;Ljava/lang/String;)Ljava/io/InputStream;'),
 ('java/lang/System','currentTimeMillis',0xb8,'harness/R','now','()J'),
 ('javax/microedition/lcdui/Canvas','repaint',0xb6,'harness/R','repaint','(Ljavax/microedition/lcdui/Canvas;)V'),
 ('java/lang/Thread','start',0xb6,'harness/R','start','(Ljava/lang/Thread;)V'),
]
def patch(src,dst):
    b=open(src,'rb').read(); n=struct.unpack('>H',b[8:10])[0]; i=10; cp=[None]; k=1
    while k<n:
        t=b[i]
        if t==1: l=struct.unpack('>H',b[i+1:i+3])[0]; cp.append((1,b[i+3:i+3+l].decode('utf8','replace'))); i+=3+l
        elif t in(3,4): cp.append((t,b[i+1:i+5])); i+=5
        elif t in(5,6): cp.append((t,b[i+1:i+9])); cp.append(None); i+=9; k+=1
        elif t in(7,8,16): cp.append((t,struct.unpack('>H',b[i+1:i+3])[0])); i+=3
        elif t in(9,10,11,12,18): cp.append((t,struct.unpack('>HH',b[i+1:i+5]))); i+=5
        elif t==15: cp.append((t,b[i+1:i+4])); i+=4
        else: raise Exception('tag %d'%t)
        k+=1
    end=i
    def u(x): return cp[x][1]
    def utf(s): s=s.encode(); return bytes([1])+struct.pack('>H',len(s))+s
    add=b''; cnt=n; rest=b[end:]; report=[]
    for (ow,nm,opc,now_,nnm,nds) in REDIRECTS:
        targets=[idx for idx,e in enumerate(cp) if e and e[0]==10 and u(u(e[1][0]))==ow and u(cp[e[1][1]][1][0])==nm]
        if not targets: continue
        base=cnt
        add+=utf(now_)+bytes([7])+struct.pack('>H',base)+utf(nnm)+utf(nds)+bytes([12])+struct.pack('>HH',base+2,base+3)+bytes([10])+struct.pack('>HH',base+1,base+4)
        newidx=base+5; cnt+=6
        for t_ in targets:
            pat=bytes([opc])+struct.pack('>H',t_); c=rest.count(pat)
            rest=rest.replace(pat,bytes([0xb8])+struct.pack('>H',newidx)); report.append('%s.%s x%d'%(ow,nm,c))
    out=b[:8]+struct.pack('>H',cnt)+b[10:end]+add+rest
    open(dst,'wb').write(out); print(src,report)
for f in sys.argv[2:]: patch(sys.argv[1]+'/'+f,'pcls/'+f)
