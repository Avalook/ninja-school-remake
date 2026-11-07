"""Prepare CFR a.java for parsing: blank out unstructured methods (run, paint), fix illegal identifiers."""
import re,sys
src=open(sys.argv[1],encoding='utf8').read().split('\n')
def blank(start_pat):
    for i,l in enumerate(src):
        if start_pat in l:
            # find end by brace counting
            depth=0; j=i; started=False
            while True:
                depth+=src[j].count('{')-src[j].count('}')
                if '{' in src[j]: started=True
                if started and depth==0: break
                j+=1
            src[i+1:j]=['        /*UNSTRUCTURED*/']*(j-i-1)
            return
blank('public final void run() {'); blank('protected final void paint(Graphics var1_1) {')
out='\n'.join(src)
KW=['do','if','for','int','new','try','case','else','byte','char','goto','long','this','void','enum','null','true','break','catch','class','const','final','float','short','super','throw','while','double','import','native','public','return','static','switch','throws','boolean','default','extends','finally','package','private','abstract','continue','strictfp','volatile','interface','protected','transient','implements','instanceof','synchronized','assert']
# illegal identifiers appear as field names: declared 'private static T kw;' and used as 'a.kw' or bare.
decl=set(re.findall(r'static [\w\[\]]+ (\w+);',out))
bad=[k for k in KW if k in decl]
print('illegal idents',bad)
for k in bad:
    out=re.sub(r'(static [\w\[\]]+ )'+k+r';',r'\1'+k+'_;',out)
    out=re.sub(r'\ba\.'+k+r'\b','a.'+k+'_',out)
    # bare uses: kw followed by assignment/index/etc where keyword grammar impossible
    out=re.sub(r'(?<![\w.])'+k+r'(?=\s*(\[|=[^=]|[-+*/%&|^]=|\+\+|--|;|\)|,|\s*[-+*/<>]|\.length))',k+'_',out)
open(sys.argv[2],'w',encoding='utf8').write(out)
