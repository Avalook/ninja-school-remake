import javax.sound.midi.*; import javax.sound.sampled.*; import java.io.*;
// Renders a MIDI file to WAV with Java's built-in Gervill software synth (no external tools).
public class Mid2Wav { public static void main(String[] a) throws Exception {
  Sequence seq=MidiSystem.getSequence(new File(a[0]));
  Synthesizer syn=MidiSystem.getSynthesizer();
  AudioFormat fmt=new AudioFormat(44100,16,2,true,false);
  AudioInputStream stream=(AudioInputStream)syn.getClass().getMethod("openStream",AudioFormat.class,java.util.Map.class).invoke(syn,fmt,null);
  if(a.length>2){ Soundbank sb=MidiSystem.getSoundbank(new File(a[2])); syn.loadAllInstruments(sb); }
  Receiver rcv=syn.getReceiver(); long len=seq.getMicrosecondLength();
  // schedule all events
  int res=seq.getResolution(); double tempo=500000; long lastTick=0; double us=0;
  java.util.List<MidiEvent> evs=new java.util.ArrayList<>(); for(Track t:seq.getTracks()) for(int i=0;i<t.size();i++) evs.add(t.get(i));
  evs.sort((x,y)->Long.compare(x.getTick(),y.getTick()));
  for(MidiEvent e:evs){ us+=(e.getTick()-lastTick)*tempo/res; lastTick=e.getTick(); MidiMessage m=e.getMessage();
    if(m instanceof MetaMessage){ MetaMessage mm=(MetaMessage)m; if(mm.getType()==0x51){ byte[] d=mm.getData(); tempo=((d[0]&255)<<16)|((d[1]&255)<<8)|(d[2]&255);} continue; }
    rcv.send(m,(long)us); }
  long frames=(long)((us/1e6+1.0)*44100);
  AudioInputStream out=new AudioInputStream(stream,fmt,frames);
  AudioSystem.write(out,AudioFileFormat.Type.WAVE,new File(a[1])); syn.close(); System.out.println(a[1]+" "+(us/1e6)+"s");
}}
