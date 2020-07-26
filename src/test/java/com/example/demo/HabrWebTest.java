package com.example.demo;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.BaseTest;

@Epic("Тестирование UI сайта Habr.com")
public class HabrWebTest extends BaseTest {

    @Test(description = "Проверка результатов поиска")
    @Feature("Поиск компаний")
    @Owner("Курдюков В. С.")
    public void checkSearchResult() {
        MainPage mainPage = new MainPage(driver.get());
        CompaniesPage companiesPage = new CompaniesPage(driver.get());
        mainPage.open()
                .moveToCompanies()
                .findOtus();
        Assert.assertEquals(companiesPage.checkSearchResult(), "OTUS", "Проверка результатов поиска");
    }

    @Test(description = "Проверка локации и что список не пустой")
    @Feature("Описание компании")
    @Owner("Курдюков В. С.")
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

    @Test(description = "Проверка ФИО QA Lead")
    @Feature("Просмотр сотрудника компании")
    @Owner("Курдюков В. С.")
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

    @Test(description = "Проверка отсутствия открытых вакансий")
    @Feature("Вакансии компании")
    @Owner("Курдюков В. С.")
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

    @Test(description = "Проверка заголовка страницы")
    @Feature("Устройство сайта")
    @Owner("Курдюков В. С.")
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

    @Test(description = "Проверка, что результат поиска содержит текст OTUS")
    @Feature("Поиск записей")
    @Owner("Курдюков В. С.")
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

    @Test(description = "Проверка, что в Хабах есть OTUS")
    @Feature("Поиск хабов")
    @Owner("Курдюков В. С.")
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

    @Test(description = "Проверка количества сотрудников OTUS")
    @Feature("Сотрудники компании")
    @Owner("Курдюков В. С.")
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

    @Test(description = "Проверка, что сотрудник подписан на блог OTUS")
    @Feature("Подписки сотрудников")
    @Owner("Курдюков В. С.")
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

    @Test(description = "Проверка языка интерфейса")
    @Feature("Язык интерфейса")
    @Owner("Курдюков В. С.")
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

    @Test(description = "Проверка языка вкладки")
    @Feature("Язык интерфейса")
    @Owner("Курдюков В. С.")
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

    @Test(description = "Проверка заголовка и что кнопка неактивна, если не заполнены все поля")
    @Feature("Регистрация")
    @Owner("Курдюков В. С.")
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

    @Test(description = "Проверка заголовка")
    @Feature("Авторизация")
    @Owner("Курдюков В. С.")
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
