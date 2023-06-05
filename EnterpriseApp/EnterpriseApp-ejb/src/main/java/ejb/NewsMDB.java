/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/MessageDrivenBean.java to edit this template
 */
package ejb;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.regex.Pattern;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/NewsQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
})
public class NewsMDB implements MessageListener {
    
    public NewsMDB() {
    }
    
    @PersistenceContext(name = "MDBLab-ejbPU")
    private EntityManager em;
    
    @Override
    public void onMessage(Message message) {
        TextMessage msg = null;
        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                NewsItem e = new NewsItem();
                String wholeText = msg.getText();
                String[] splitText = wholeText.split(Pattern.quote("|"));
                e.setBody(splitText[0]);
                e.setHeading(splitText[1]);
                em.persist(e);
        }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
}
