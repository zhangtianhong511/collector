  package  com.kkwl.collector.protocols.IEC101;
  
  import com.kkwl.collector.common.FrameType;
  import com.kkwl.collector.protocols.BaseFrameParser;
  import java.util.HashMap;
  import java.util.Map;
  
  
  
  
  public class ResetAckFrameParser
    extends BaseFrameParser
  {
    public ResetAckFrameParser() { super(FrameType.IEC101_RESET_ACK_FRAME.value()); }
  
  
    
    public Map<String, Object> parseBytesFrame(byte[] frameBytes, Map<String, Object> params) {
      Map<String, Object> retMap = new HashMap<String, Object>();
      int address = ((Integer)params.get("address")).intValue();
      
      if (frameBytes[1] == Byte.MIN_VALUE && frameBytes[5] == 22) {
        int addressLo = frameBytes[2] & 0xFF;
        int addressHi = frameBytes[3] & 0xFF;
        int paramAddress = (addressHi << 8) + addressLo;
        
        if (paramAddress == address) {
          retMap.put("type", FrameType.IEC101_RESET_ACK_FRAME);
          retMap.put("result", Boolean.valueOf(true));
        } else {
          retMap.put("result", Boolean.valueOf(false));
        } 
      } else {
        retMap.put("result", Boolean.valueOf(false));
      } 
      
      return retMap;
    }
  }


/* Location:              C:\Users\Andy\Desktop\EMS\server-2\kk\collector\collector-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\kkwl\collector\protocols\IEC101\ResetAckFrameParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */