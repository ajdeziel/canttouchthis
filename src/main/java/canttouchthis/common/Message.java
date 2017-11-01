package canttouchthis.common;

import java.util.Date;
import java.io.Serializable;

/**
 * Container class for chat messages. This will likely change as more crypto
 * stuff gets added.
 */
public class Message implements Serializable {

    public final String sender;
    public final String reciever;
    public final Date timestamp;
    public final String message;

    public Message(String from, String to, long ts, String msg) {
        sender = from;
        reciever = to;
        timestamp = new Date(ts);
        message = msg;
    }


}
