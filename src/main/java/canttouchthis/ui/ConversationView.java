package canttouchthis.ui;

import canttouchthis.common.ChatMessage;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import javax.swing.*;

/**
 * Window for sending and recieving chat messages (client or server side).
 */
public class ConversationView extends JFrame {

    protected ConversationModel model;

    private final int FRAME_WIDTH = 300;
    private final int FRAME_HEIGHT = 400;
    private final int COMP_INSETS = 4;

    private final SimpleDateFormat date_format = new SimpleDateFormat("HH:mm:ss");

    protected JTextPane chatPane;
    protected JTextField sendField;
    protected JButton sendButton;

    /**
     * Create a new ConversationView window from a given model.
     *
     * @param model Structure containing message data.
     */
    public ConversationView(ConversationModel model) {
        super("canttouchthis v1.0");

        this.model = model;

        getRootPane().setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        setResizable(false);
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set grid bag layout and create contraints object
        Container pane = getContentPane();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // set external padding
        c.insets = new Insets(COMP_INSETS, COMP_INSETS, COMP_INSETS, COMP_INSETS);

        // chat window
        chatPane = new JTextPane();
        JScrollPane chatScroll = new JScrollPane(chatPane);

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 0.95;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        chatPane.setEditable(false);
        pane.add(chatScroll, c);

        c.gridwidth = 1;

        // send field and button
        sendField = new JTextField(20);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.9;
        c.weighty = 0.05;
        c.ipadx = 2;
        c.ipady = 20;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        pane.add(sendField, c);

        sendButton = new JButton("Send");
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.1;
        c.weighty = 0.05;
        c.ipadx = 2;
        c.ipady = 20;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        pane.add(sendButton, c);

        // add all messages already in model
        this.updateConversation();

        pack();
    }

    /**
     * Update all messages in the view from the model.
     */
    public void updateConversation() {
        // re-render all messages
        chatPane.setText("");
        Iterator<ChatMessage> i = model.messages();
        while (i.hasNext()) {
            renderMessage(i.next());
        }
    }

    /**
     * Render a warning to the chat view.
     * @param message ChatMessage to be displayed after "WARNING: "
     */
    protected void renderWarningMessage(String message) {
        String rendered = "WARNING: " + message + "\n";
        chatPane.setText(chatPane.getText() + rendered);
    }

    /**
     * Render a ChatMessage object to the view.
     * @param m ChatMessage to be rendered.
     */
    protected void renderMessage(ChatMessage m) {
        String rendered = String.format("[%s] %s: %s\n", date_format.format(m.timestamp),
                m.getSenderName(), m.message);

        // TODO: do this more efficiently and add styling
        chatPane.setText(chatPane.getText() + rendered);
        chatPane.setCaretPosition(chatPane.getDocument().getLength());
    }

}
