package cases;

import listeners.ExecutionListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utils.BaseHooks;

@Listeners(ExecutionListener.class)
public class SampleTest extends BaseHooks {

    String firstName = "Вадим";
    String secondName = "Курдюков";
    String whatsAppContact = "123";
    String faceBookContact = "123";

    @Test
    public void sampleTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open()
                .login()
                .switchProfile()
                .addInform(firstName, secondName, whatsAppContact, faceBookContact);
        loginPage.newBrowser()
                .open()
                .login()
                .switchProfile();

        ProfilePage profilePage = new ProfilePage(driver);

        Assert.assertEquals(profilePage.getName(), firstName);
        Assert.assertEquals(profilePage.getSurname(), secondName);
        Assert.assertEquals(profilePage.getCheckFaceBook(), faceBookContact);
        Assert.assertEquals(profilePage.getCheckWhatsApp(), whatsAppContact);

    }


}
