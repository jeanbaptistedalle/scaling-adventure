package reseau.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.assertEquals;

public class MessageTest {
    @Test
    public void validMessage(){
        // Teste la conservation du message après sa transformation en byte[] et sa reconversion en Message
        byte [] from_orig = {1,2,3,4};
        Constant.command cmd_orig = Constant.command.SUBMIT;
        String content_orig = "Test";
        
        Message message_orig = new Message(from_orig, cmd_orig, content_orig);
        Message message_recv = new Message(message_orig.toByteArray());
        try {
            assertEquals(InetAddress.getByAddress(from_orig), message_recv.getFrom());
        } catch (UnknownHostException ex) {
            Logger.getLogger(MessageTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(cmd_orig, message_recv.getCmd());
        assertEquals(content_orig, message_recv.getContent());
    }
}
