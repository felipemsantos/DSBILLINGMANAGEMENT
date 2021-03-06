package org.tmf.dsmapi.billingAccount.event;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import static org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.ANY;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.tmf.dsmapi.commons.utils.CustomJsonDateSerializer;
import org.tmf.dsmapi.billingAccount.model.BillingAccount;

@XmlRootElement
@Entity
@Table(name="Event_BillingAccount")
@JsonPropertyOrder(value = {"id", "eventTime", "eventType", "event"})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BillingAccountEvent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @JsonIgnore
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = CustomJsonDateSerializer.class)
    private Date eventTime;

    @Enumerated(value = EnumType.STRING)
    private BillingAccountEventTypeEnum eventType;

    private BillingAccount resource; //check for object

   @JsonProperty("eventId")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
      
    @JsonAutoDetect(fieldVisibility = ANY)
    class EventBody {
        private BillingAccount billingAccount;
        public BillingAccount getBillingAccount() {
            return billingAccount;
        }
        public EventBody(BillingAccount billingAccount) { 
        this.billingAccount = billingAccount;
    }
    
       
    }
   @JsonProperty("event")
   public EventBody getEvent() {
       
       return new EventBody(getResource() );
   }
    

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public BillingAccountEventTypeEnum getEventType() {
        return eventType;
    }

    public void setEventType(BillingAccountEventTypeEnum eventType) {
        this.eventType = eventType;
    }

   @JsonIgnore
    public BillingAccount getResource() {
        
        
        return resource;
    }

    public void setResource(BillingAccount resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "BillingAccountEvent{" + "id=" + id + ", eventTime=" + eventTime + ", eventType=" + eventType + ", resource=" + resource + '}';
    }


   

}
