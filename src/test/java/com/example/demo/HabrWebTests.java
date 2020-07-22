package com.example.demo;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.BaseTest;

public class HabrWebTests extends BaseTest {

    @Test
    public void checkSearchResult() {
        MainPage mainPage = new MainPage(driver.get());
        CompaniesPage companiesPage = new CompaniesPage(driver.get());
        mainPage.open()
                .moveToCompanies()
                .findOtus();
        Assert.assertEquals(companiesPage.checkSearchResult(), "OTUS", "Проверка результатов поиска");
    }

    @Test
    public void checkLocation() {
        MainPage mainPage = new MainPage(driver.get());
        CompaniesPage companiesPage = new CompaniesPage(driver.get());
        mainPage.open()
                .moveToCompanies()
                .findOtus()
                .openOtus();
        Assert.assertTrue(companiesPage.checkLocation().contains("Москва"), "Проверка локации");
        Assert.assertNotNull(companiesPage.checkContentList(), "Проверка, что список не пустой");
    }

    @Test
    public void checkQaLead() {
        MainPage mainPage = new MainPage(driver.get());
        CompaniesPage companiesPage = new CompaniesPage(driver.get());
        mainPage.open()
                .moveToCompanies()
                .findOtus()
                .openOtus()
                .moveToEmployees();
        Assert.assertTrue(companiesPage.checkQaLead().contains("Семён Вяземский"), "Проверка ФИО QA Lead");
    }

    @Test
    public void checkVacancy() {
        MainPage mainPage = new MainPage(driver.get());
        CompaniesPage companiesPage = new CompaniesPage(driver.get());
        mainPage.open()
                .moveToCompanies()
                .findOtus()
                .openOtus()
                .moveToEmployees()
                .moveToVacancies();
        Assert.assertTrue(companiesPage.checkVacanciesListNull().contains("нет открытых вакансий") || companiesPage.checkVacanciesListNull().contains("The company has no open vacancies on") , "Проверка отсутствия открытых вакансий");
    }

    @Test
    public void checkTitle() {
        MainPage mainPage = new MainPage(driver.get());
        SiteInformPage siteInformPage = new SiteInformPage(driver.get());
        mainPage.open()
                .moveToCompanies()
                .findOtus()
                .openOtus()
                .moveToEmployees()
                .moveToVacancies()
                .moveToSiteInform();
        Assert.assertTrue(siteInformPage.checkTitle().contains("Информация") || siteInformPage.checkTitle().contains("Info"), "Проверка заголовка страницы");
    }

    @Test
    public void checkSearchResultContainsOtus() {
        MainPage mainPage = new MainPage(driver.get());
        SearchPage searchPage = new SearchPage(driver.get());
        mainPage.open()
                .moveToCompanies()
                .findOtus()
                .openOtus()
                .moveToEmployees()
                .moveToVacancies()
                .moveToSiteInform()
                .searchOtusNotes();
        Assert.assertTrue(searchPage.checkFirstPost().toLowerCase().contains("otus"), "Проверка, что результат поиска содержит текст OTUS");
    }

    @Test
    public void checkHubContainsOtus() {
        MainPage mainPage = new MainPage(driver.get());
        SearchPage searchPage = new SearchPage(driver.get());
        mainPage.open()
                .moveToCompanies()
                .findOtus()
                .openOtus()
                .moveToEmployees()
                .moveToVacancies()
                .moveToSiteInform()
                .searchOtusNotes()
                .moveToHub();
        Assert.assertTrue(searchPage.checkOtusBlog().toLowerCase().contains("otus"), "Проверка, что в Хабах есть OTUS");

    }

    @Test
    public void checkCountEmployee() {
        MainPage mainPage = new MainPage(driver.get());
        SearchPage searchPage = new SearchPage(driver.get());
        mainPage.open()
                .moveToCompanies()
                .findOtus()
                .openOtus()
                .moveToEmployees()
                .moveToVacancies()
                .moveToSiteInform()
                .searchOtusNotes()
                .moveToHub()
                .moveToUsers();
        Assert.assertEquals(searchPage.checkUsersCount(), 6, "Проверка количества сотрудников OTUS");
    }

    @Test
    public void checkEmployeeSubscribe() {
        MainPage mainPage = new MainPage(driver.get());
        SearchPage searchPage = new SearchPage(driver.get());
        mainPage.open()
                .moveToCompanies()
                .findOtus()
                .openOtus()
                .moveToEmployees()
                .moveToVacancies()
                .moveToSiteInform()
                .searchOtusNotes()
                .moveToHub()
                .moveToUsers()
                .moveToOtusUser();
        Assert.assertEquals(searchPage.checkSubs(), "OTUS. Онлайн-образование", "Проверка, что сотрудник подписан на блог OTUS");
    }

    @Test
    public void checkInterfaceLang() {
        MainPage mainPage = new MainPage(driver.get());
        SearchPage searchPage = new SearchPage(driver.get());
        mainPage.open()
                .moveToCompanies()
                .findOtus()
                .openOtus()
                .moveToEmployees()
                .moveToVacancies()
                .moveToSiteInform()
                .searchOtusNotes()
                .moveToHub()
                .moveToUsers()
                .moveToOtusUser()
                .setLanguage();
        Assert.assertTrue(searchPage.checkInterfaceLang().contains("bookmarks"), "Проверка языка интерфейса");
    }

    @Test
    public void checkTabLang() {
        MainPage mainPage = new MainPage(driver.get());
        ManagementPage managementPage = new ManagementPage(driver.get());
        mainPage.open()
                .moveToCompanies()
                .findOtus()
                .openOtus()
                .moveToEmployees()
                .moveToVacancies()
                .moveToSiteInform()
                .searchOtusNotes()
                .moveToHub()
                .moveToUsers()
                .moveToOtusUser()
                .setLanguage()
                .moveToManagement();
        Assert.assertTrue(managementPage.checkTabLang().contains("articles"), "Проверка языка вкладки");
    }

    @Test
    public void checkBtn() {
        MainPage mainPage = new MainPage(driver.get());
        SignUpPage signUpPage = new SignUpPage(driver.get());
        mainPage.open()
                .moveToCompanies()
                .findOtus()
                .openOtus()
                .moveToEmployees()
                .moveToVacancies()
                .moveToSiteInform()
                .searchOtusNotes()
                .moveToHub()
                .moveToUsers()
                .moveToOtusUser()
                .setLanguage()
                .moveToManagement()
                .moveToReg();
        Assert.assertTrue(signUpPage.checkTitle().toLowerCase().contains("registration"), "Проверка заголовка");
        Assert.assertFalse(signUpPage.checkSignUpBtn(), "Проверка, что кнопка неактивна, если не заполнены все поля");
    }

    @Test
    public void checkTitleLogIn() {
        MainPage mainPage = new MainPage(driver.get());
        LogInPage logInPage = new LogInPage(driver.get());
        mainPage.open()
                .moveToCompanies()
                .findOtus()
                .openOtus()
                .moveToEmployees()
                .moveToVacancies()
                .moveToSiteInform()
                .searchOtusNotes()
                .moveToHub()
                .moveToUsers()
                .moveToOtusUser()
                .setLanguage()
                .moveToManagement()
                .moveToReg()
                .moveToLogIn();
        Assert.assertTrue(logInPage.checkTitle().equalsIgnoreCase("log in"), "Проверка заголовка");
    }
}
