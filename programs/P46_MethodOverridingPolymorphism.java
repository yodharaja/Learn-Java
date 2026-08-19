package programs;

/**
 * ============================================================
 * PROGRAM 46: Method Overriding and Runtime Polymorphism
 * ============================================================
 * Problem: WAP to demonstrate dynamic method dispatch where
 * a superclass reference invokes overridden methods of different subclasses.
 * ============================================================
 */

class Notification {
    public void send(String message) {
        System.out.println("  [Base] Generic notification: " + message);
    }
}

class EmailNotification extends Notification {
    @Override
    public void send(String message) {
        System.out.println("  📧 [Email] Sending email with subject/body: " + message);
    }
}

class SmsNotification extends Notification {
    @Override
    public void send(String message) {
        System.out.println("  📱 [SMS] Sending 160-char SMS: " + message);
    }
}

class PushNotification extends Notification {
    @Override
    public void send(String message) {
        System.out.println("  🔔 [Push] Sending mobile push notification: " + message);
    }
}

public class P46_MethodOverridingPolymorphism {

    public static void main(String[] args) {
        Notification[] channels = {
            new EmailNotification(),
            new SmsNotification(),
            new PushNotification()
        };

        System.out.println("=== RUNTIME POLYMORPHISM (DYNAMIC DISPATCH) ===");
        for (Notification channel : channels) {
            channel.send("Your security code is 987-123");
        }
    }
}
