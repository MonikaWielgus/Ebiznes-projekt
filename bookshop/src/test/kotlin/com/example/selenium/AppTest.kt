package com.example.selenium

import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestAmazonCart {

    private val BASE_URL = "http://localhost:3000"

    private val driver = WebDriverManager.chromedriver().create()

    @AfterAll
    fun `close browser`() {
        driver.close()
    }

    @Test
    fun `open login page`() {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login_icon")))
        val loginIcon = driver.findElement(By.id("login_icon"))
        loginIcon.click()

        val actualUrl = driver.currentUrl
        val expectedUrl = "$BASE_URL/login"

        //TODO
        Assertions.assertEquals(expectedUrl, actualUrl)
    }

    @Test
    fun `open shopping cart page when not logged in`() {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cart_icon")))
        val cartIcon = driver.findElement(By.id("cart_icon"))
        cartIcon.click()

        val actualUrl = driver.currentUrl
        val expectedUrl = "$BASE_URL/login"

        //TODO
        Assertions.assertEquals(expectedUrl, actualUrl)
    }

    @Test
    fun `login buttons show up on login page`() {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login_icon")))
        val loginIcon = driver.findElement(By.id("login_icon"))
        loginIcon.click()
        val googleButtonDisplayed = driver.findElement(By.id("google_button")).isDisplayed
        val githubButtonDisplayed = driver.findElement(By.id("github_button")).isDisplayed

        //TODO
        Assertions.assertTrue(googleButtonDisplayed)
        //TODO
        Assertions.assertTrue(githubButtonDisplayed)
    }

    @Test
    fun `go back to main page from login page` () {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login_icon")))
        var element = driver.findElement(By.id("login_icon"))
        element.click()
        element = driver.findElement(By.id("back_to_main_page"))
        element.click()

        val actualUrl = driver.currentUrl
        val expectedUrl = "$BASE_URL/"

        //TODO
        Assertions.assertEquals(expectedUrl, actualUrl)
    }

    @Test
    fun `menu is rendered`() {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu")))
        val menuDisplayed = driver.findElement(By.id("menu")).isDisplayed

        //TODO
        Assertions.assertTrue(menuDisplayed)
    }

    @Test
    fun `tabList is rendered` () {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("tabs")))
        val tabsDisplayed = driver.findElement(By.id("tabs")).isDisplayed

        //TODO
        Assertions.assertTrue(tabsDisplayed)
    }

    @Test
    fun `tabs is rendered` () {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div[1]/div/div/div/button[1]")))
        val firstTabDisplayed = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/div/div/button[1]")).isDisplayed
        val secondTabDisplayed = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/div/div/button[2]")).isDisplayed
        val thirdTabDisplayed = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/div/div/button[3]")).isDisplayed

        //TODO
        Assertions.assertTrue(firstTabDisplayed)
        //TODO
        Assertions.assertTrue(secondTabDisplayed)
        //TODO
        Assertions.assertTrue(thirdTabDisplayed)
    }

    @Test
    fun `correct tab is displayed` () {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("tab_1")))
        val tabDisplayed = driver.findElement(By.id("tab_1")).isDisplayed

        //TODO
        Assertions.assertTrue(tabDisplayed)
    }

    @Test
    fun `change tab works` () {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("tab_1")))

        var tabDisplayed = driver.findElement(By.id("tab_1")).isDisplayed
        //TODO
        Assertions.assertTrue(tabDisplayed)

        val secondTabButton = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/div/div/button[2]"))
        secondTabButton.click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("tab_2")))

        tabDisplayed = driver.findElement(By.id("tab_2")).isDisplayed
        //TODO
        Assertions.assertTrue(tabDisplayed)
    }

    @Test
    fun `books in category tab are rendered` () {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_1")))
        val bookDisplayed = driver.findElement(By.id("book_1")).isDisplayed
        val bookInfoDisplayed = driver.findElement(By.id("book_info_icon_1")).isDisplayed
        val addToCartDisplayed = driver.findElement(By.id("add_to_cart_icon_1")).isDisplayed

        //TODO
        Assertions.assertTrue(bookDisplayed)
        //TODO
        Assertions.assertTrue(bookInfoDisplayed)
        //TODO
        Assertions.assertTrue(addToCartDisplayed)
    }

    @Test
    fun `book title displays correctly` () {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_panel_title_1")))
        val titleDisplayed = driver.findElement(By.id("book_panel_title_1"))

        val expectedTitle = "Jakaś książka"
        val actualTitle = titleDisplayed.text

        //TODO
        Assertions.assertEquals(expectedTitle, actualTitle)
    }

    @Test
    fun `book page renders correctly` () {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_info_icon_1")))

        val bookInfo = driver.findElement(By.id("book_info_icon_1"))
        bookInfo.click()
        val actualUrl = driver.currentUrl
        val expectedUrl = "$BASE_URL/book/1"

        wait.until(ExpectedConditions.elementToBeClickable(By.id("title_header_1")))

        val titleHeader = driver.findElement(By.id("title_header_1"))
        val title = driver.findElement(By.id("book_title_1"))
        val authorHeader = driver.findElement(By.id("author_header_1"))
        val author = driver.findElement(By.id("book_author_1"))
        val categoryHeader = driver.findElement(By.id("category_header_1"))
        val category = driver.findElement(By.id("book_category_1"))
        val priceHeader = driver.findElement(By.id("price_header_1"))
        val price = driver.findElement(By.id("book_price_1"))
        val addToCartButton = driver.findElement(By.id("book_page_add_to_cart_1"))

        //TODO
        Assertions.assertEquals(expectedUrl, actualUrl)
        //TODO
        Assertions.assertTrue(titleHeader.isDisplayed)
        //TODO
        Assertions.assertEquals("Tytuł", titleHeader.text)
        //TODO
        Assertions.assertTrue(title.isDisplayed)
        //TODO
        Assertions.assertEquals("Jakaś książka", title.text)
        //TODO
        Assertions.assertTrue(authorHeader.isDisplayed)
        //TODO
        Assertions.assertEquals("Autor", authorHeader.text)
        //TODO
        Assertions.assertTrue(author.isDisplayed)
        //TODO
        Assertions.assertEquals("Jakiś autor", author.text)
        //TODO
        Assertions.assertTrue(categoryHeader.isDisplayed)
        //TODO
        Assertions.assertEquals("Kategoria", categoryHeader.text)
        //TODO
        Assertions.assertTrue(category.isDisplayed)
        //TODO
        Assertions.assertEquals("Romans", category.text)
        //TODO
        Assertions.assertTrue(priceHeader.isDisplayed)
        //TODO
        Assertions.assertEquals("Cena", priceHeader.text)
        //TODO
        Assertions.assertTrue(price.isDisplayed)
        //TODO
        Assertions.assertEquals("11.59", price.text)
        //TODO
        Assertions.assertTrue(addToCartButton.isDisplayed)
    }

    @Test
    fun `add to cart from category page when not logged in`() {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add_to_cart_icon_1")))
        val addToCart = driver.findElement(By.id("add_to_cart_icon_1"))
        addToCart.click()

        val actualUrl = driver.currentUrl
        val expectedUrl = "$BASE_URL/login"

        //TODO
        Assertions.assertEquals(expectedUrl, actualUrl)
    }

    @Test
    fun `add to cart from book page when not logged in`() {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_info_icon_1")))
        val bookInfo = driver.findElement(By.id("book_info_icon_1"))
        bookInfo.click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_page_add_to_cart_1")))
        val addToCartButton = driver.findElement(By.id("book_page_add_to_cart_1"))
        addToCartButton.click()

        val actualUrl = driver.currentUrl
        val expectedUrl = "$BASE_URL/login"

        //TODO
        Assertions.assertEquals(expectedUrl, actualUrl)
    }
}