import struct,sys
def patch(src,dst):
    b=open(src,'rb').read(); n=struct.unpack('>H',b[8:10])[0]; i=10; cp=[None]
    k=1
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
    target=None
    for idx,e in enumerate(cp):
        if e and e[0]==10:
            c,nt=e[1]; cname=u(u(c)); nm,ds=cp[nt][1]
            if cname=='java/lang/Class' and u(nm)=='getResourceAsStream': target=idx
    if target is None: open(dst,'wb').write(b); print(src,'no target'); return
    def utf(s): s=s.encode(); return bytes([1])+struct.pack('>H',len(s))+s
    base=n
    add=utf('harness/R')+bytes([7])+struct.pack('>H',base)+utf('g')+utf('(Ljava/lang/Class;Ljava/lang/String;)Ljava/io/InputStream;')+bytes([12])+struct.pack('>HH',base+2,base+3)+bytes([10])+struct.pack('>HH',base+1,base+4)
    newidx=base+5
    rest=b[end:]; pat=bytes([0xb6])+struct.pack('>H',target); cnt=rest.count(pat)
    rest=rest.replace(pat,bytes([0xb8])+struct.pack('>H',newidx))
    out=b[:8]+struct.pack('>H',n+6)+b[10:end]+add+rest
    open(dst,'wb').write(out); print(src,'patched',cnt)
for f in sys.argv[2:]: patch(sys.argv[1]+'/'+f, 'pcls/'+f)
