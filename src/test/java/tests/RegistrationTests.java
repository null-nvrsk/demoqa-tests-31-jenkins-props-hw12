package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Tag("demoqa")
public class RegistrationTests extends BaseTest {

    @Test
    void successRegistrationTest() {
        step("Open form", () -> {
            registrationPage.openPage();
        });

        step("Fill form", () -> {
            registrationPage
                    .setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setEmail(testData.userEmail)
                    .setGender(testData.userGender)
                    .setPhone(testData.userNumber)
                    .setBirthDate(testData.userBirthDay, testData.userBirthMonth, testData.userBirthYear)
                    .setSubject(testData.subject)
                    .setHobby(testData.hobby)
                    .uploadPicture(testData.picturePath)
                    .setCurrentAddress(testData.currentAddress)
                    .setStateAndCity(testData.state, testData.city);

            registrationPage.submitRegistrationForm();
        });

        step("verifyResults", () -> {
            registrationPage
                    .verifyResultsModal()
                    .verifyResult("Student Name", testData.firstName + " " + testData.lastName)
                    .verifyResult("Student Email", testData.userEmail)
                    .verifyResult("Gender", testData.userGender)
                    .verifyResult("Mobile", testData.userNumber)
                    .verifyResult("Date of Birth", testData.userBirthDay + " " + testData.userBirthMonth + ","
                            + testData.userBirthYear)
                    .verifyResult("Subjects", testData.subject)
                    .verifyResult("Hobbies", testData.hobby)
                    .verifyResult("Picture", testData.pictureName)
                    .verifyResult("Address", testData.currentAddress)
                    .verifyResult("State and City", testData.state + " " + testData.city);
        });
    }

    @Test
    void minimalRequiredRegistrationTest() {
        step("Open form", () -> {
            registrationPage.openPage();
        });

        step("Fill minimal form", () -> {
            registrationPage
                    .setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setGender(testData.userGender)
                    .setPhone(testData.userNumber);

            registrationPage.submitRegistrationForm();
        });

        step("verifyResults", () -> {
            registrationPage.verifyResultsModal()
                    .verifyResult("Student Name", testData.firstName + " " + testData.lastName)
                    .verifyResult("Gender", testData.userGender)
                    .verifyResult("Mobile", testData.userNumber);
        });
    }

    @Test
    void invalidEmailRegistrationTest() {
        step("Open form", () -> {
            registrationPage.openPage();
        });

        step("Fill form with invalid email", () -> {
            registrationPage
                    .setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setEmail(testData.invalidEmail)
                    .setGender(testData.userGender)
                    .setPhone(testData.userNumber);

            registrationPage.submitRegistrationForm();
        });

        step("verifyResults", () -> {
            registrationPage.verifyEmailErrorNotification();
        });
    }
}