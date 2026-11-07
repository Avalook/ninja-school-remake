package javax.microedition.rms;
import java.util.*; import java.io.*;
public class RecordStore { static Map<String,RecordStore> all=new HashMap<>(); String n; List<byte[]> recs=new ArrayList<>();
  public static RecordStore openRecordStore(String n,boolean c) throws RecordStoreException { RecordStore r=all.get(n); if(r==null){ r=load(n); if(r==null){ if(!c) throw new RecordStoreNotFoundException(); r=new RecordStore(); r.n=n;} all.put(n,r);} return r; }
  static RecordStore load(String n){ File f=new File(harness.Emu.rmsDir,n+".rms"); if(!f.exists())return null; try{ DataInputStream in=new DataInputStream(new FileInputStream(f)); RecordStore r=new RecordStore(); r.n=n; int c=in.readInt(); for(int i=0;i<c;i++){int l=in.readInt(); if(l<0){r.recs.add(null);continue;} byte[] b=new byte[l]; in.readFully(b); r.recs.add(b);} in.close(); return r;}catch(Exception e){return null;} }
  void save(){ try{ new File(harness.Emu.rmsDir).mkdirs(); DataOutputStream o=new DataOutputStream(new FileOutputStream(new File(harness.Emu.rmsDir,n+".rms"))); o.writeInt(recs.size()); for(byte[] b:recs){ if(b==null){o.writeInt(-1);continue;} o.writeInt(b.length); o.write(b);} o.close(); }catch(Exception e){} }
  public int addRecord(byte[] d,int o,int l){ recs.add(Arrays.copyOfRange(d,o,o+l)); save(); return recs.size(); }
  public void setRecord(int id,byte[] d,int o,int l){ recs.set(id-1,Arrays.copyOfRange(d,o,o+l)); save(); }
  public byte[] getRecord(int id) throws InvalidRecordIDException { if(id<1||id>recs.size()||recs.get(id-1)==null) throw new InvalidRecordIDException(); return recs.get(id-1).clone(); }
  public int getNumRecords(){ return recs.size(); } public void closeRecordStore(){} public int getNextRecordID(){return recs.size()+1;}
  public void deleteRecord(int id){ recs.set(id-1,null); save(); }
  public static void deleteRecordStore(String n){ all.remove(n); new File(harness.Emu.rmsDir,n+".rms").delete(); }
}
