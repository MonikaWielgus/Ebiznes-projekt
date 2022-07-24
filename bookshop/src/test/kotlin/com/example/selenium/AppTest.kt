package com.example.selenium

import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.api.*
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestAmazonCart {

    private val BASE_URL = "http://localhost:3000"
    private val firstBookTitle = "Jakaś książka"
    private val firstBookAuthor = "Jakiś autor"
    private val actualFirstBookPrice = "11.59 zł"
    private val actualSecondBookPrice = "20.30 zł"

    private lateinit var driver: WebDriver

    @BeforeEach
    fun openBrowser() {
        driver = WebDriverManager.chromedriver().create()
    }

    @AfterEach
    fun closeBrowser() {
        driver.close()
    }

    @Test
    fun titleVisible() {
        driver.get(BASE_URL)
        val titleVisible = driver.findElement(By.id("title")).isDisplayed

        Assertions.assertTrue(titleVisible)
    }

    @Test
    fun barVisibleOnMainPage() {
        driver.get(BASE_URL)
        val barVisible = driver.findElement(By.id("bar")).isDisplayed

        Assertions.assertTrue(barVisible)
    }

    @Test
    fun barVisibleOnCartPage() {
        googleLogin()
        val wait = WebDriverWait(driver, Duration.ofSeconds(2))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cart_icon")))
        val cartIcon = driver.findElement(By.id("cart_icon"))
        cartIcon.click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("empty_cart")))
        val barVisible = driver.findElement(By.id("bar")).isDisplayed

        Assertions.assertTrue(barVisible)
    }

    @Test
    fun barVisibleOnBookPage() {
        googleLogin()
        val wait = WebDriverWait(driver, Duration.ofSeconds(2))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_info_icon_1")))
        val bookInfo = driver.findElement(By.id("book_info_icon_1"))
        bookInfo.click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("bar")))
        val barVisible = driver.findElement(By.id("bar")).isDisplayed

        Assertions.assertTrue(barVisible)
    }

    @Test
    fun openLoginPage() {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login_icon")))
        val loginIcon = driver.findElement(By.id("login_icon"))
        loginIcon.click()

        val actualUrl = driver.currentUrl
        val expectedUrl = "$BASE_URL/login"

        Assertions.assertEquals(expectedUrl, actualUrl)
    }

    @Test
    fun openShoppingCartPageWhenNotLoggedIn() {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cart_icon")))
        val cartIcon = driver.findElement(By.id("cart_icon"))
        cartIcon.click()

        val actualUrl = driver.currentUrl
        val expectedUrl = "$BASE_URL/login"

        Assertions.assertEquals(expectedUrl, actualUrl)
    }

    @Test
    fun loginButtonsShowUpOnLoginPage() {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login_icon")))
        val loginIcon = driver.findElement(By.id("login_icon"))
        loginIcon.click()
        val googleButtonDisplayed = driver.findElement(By.id("google_button")).isDisplayed
        val githubButtonDisplayed = driver.findElement(By.id("github_button")).isDisplayed

        Assertions.assertTrue(googleButtonDisplayed)
        Assertions.assertTrue(githubButtonDisplayed)
    }

    @Test
    fun goBackToMainPageFromLoginPage () {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login_icon")))
        var element = driver.findElement(By.id("login_icon"))
        element.click()
        element = driver.findElement(By.id("back_to_main_page"))
        element.click()

        val actualUrl = driver.currentUrl
        val expectedUrl = "$BASE_URL/"

        Assertions.assertEquals(expectedUrl, actualUrl)
    }

    @Test
    fun menuIsRendered() {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu")))
        val menuDisplayed = driver.findElement(By.id("menu")).isDisplayed

        Assertions.assertTrue(menuDisplayed)
    }

    @Test
    fun tabListIsRendered () {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("tabs")))
        val tabsDisplayed = driver.findElement(By.id("tabs")).isDisplayed

        Assertions.assertTrue(tabsDisplayed)
    }

    @Test
    fun tabsAreRendered () {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div[1]/div/div/div/button[1]")))
        val firstTabDisplayed = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/div/div/button[1]")).isDisplayed
        val secondTabDisplayed = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/div/div/button[2]")).isDisplayed
        val thirdTabDisplayed = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/div/div/button[3]")).isDisplayed

        Assertions.assertTrue(firstTabDisplayed)
        Assertions.assertTrue(secondTabDisplayed)
        Assertions.assertTrue(thirdTabDisplayed)
    }

    @Test
    fun correctTabIsDisplayed () {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("tab_1")))
        val tabDisplayed = driver.findElement(By.id("tab_1")).isDisplayed

        Assertions.assertTrue(tabDisplayed)
    }

    @Test
    fun changeTabWorks () {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("tab_1")))

        var tabDisplayed = driver.findElement(By.id("tab_1")).isDisplayed
        Assertions.assertTrue(tabDisplayed)

        val secondTabButton = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/div/div/button[2]"))
        secondTabButton.click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("tab_2")))

        tabDisplayed = driver.findElement(By.id("tab_2")).isDisplayed

        Assertions.assertTrue(tabDisplayed)
    }

    @Test
    fun booksInCategoryTabAreRendered () {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_1")))
        val bookDisplayed = driver.findElement(By.id("book_1")).isDisplayed
        val bookInfoDisplayed = driver.findElement(By.id("book_info_icon_1")).isDisplayed
        val addToCartDisplayed = driver.findElement(By.id("add_to_cart_icon_1")).isDisplayed

        Assertions.assertTrue(bookDisplayed)
        Assertions.assertTrue(bookInfoDisplayed)
        Assertions.assertTrue(addToCartDisplayed)
    }

    @Test
    fun bookTitleDisplaysCorrectly () {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_panel_title_1")))
        val titleDisplayed = driver.findElement(By.id("book_panel_title_1"))

        val expectedTitle = firstBookTitle
        val actualTitle = titleDisplayed.text

        Assertions.assertEquals(expectedTitle, actualTitle)
    }

    @Test
    fun bookPageRendersCorrectly () {
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

        Assertions.assertEquals(expectedUrl, actualUrl)
        Assertions.assertTrue(titleHeader.isDisplayed)
        Assertions.assertEquals("Tytuł", titleHeader.text)
        Assertions.assertTrue(title.isDisplayed)
        Assertions.assertEquals(firstBookTitle, title.text)
        Assertions.assertTrue(authorHeader.isDisplayed)
        Assertions.assertEquals("Autor", authorHeader.text)
        Assertions.assertTrue(author.isDisplayed)
        Assertions.assertEquals(firstBookAuthor, author.text)
        Assertions.assertTrue(categoryHeader.isDisplayed)
        Assertions.assertEquals("Kategoria", categoryHeader.text)
        Assertions.assertTrue(category.isDisplayed)
        Assertions.assertEquals("Romans", category.text)
        Assertions.assertTrue(priceHeader.isDisplayed)
        Assertions.assertEquals("Cena", priceHeader.text)
        Assertions.assertTrue(price.isDisplayed)
        Assertions.assertEquals(actualFirstBookPrice, price.text)
        Assertions.assertTrue(addToCartButton.isDisplayed)
    }

    @Test
    fun addToCartFromCategoryPageWhenNotLoggedIn() {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add_to_cart_icon_1")))
        val addToCart = driver.findElement(By.id("add_to_cart_icon_1"))
        addToCart.click()

        val actualUrl = driver.currentUrl
        val expectedUrl = "$BASE_URL/login"

        Assertions.assertEquals(expectedUrl, actualUrl)
    }

    @Test
    fun addToCartFromBookPageWhenNotLoggedIn() {
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

        Assertions.assertEquals(expectedUrl, actualUrl)
    }

    @Test
    fun testGoogleLogIn () {
        googleLogin()
        val actualUrl = driver.currentUrl

        Assertions.assertEquals("$BASE_URL/", actualUrl)
        logout()
    }

    @Test
    fun testGithubLogIn () {
        githubLogin()
        val actualUrl = driver.currentUrl

        Assertions.assertEquals("$BASE_URL/", actualUrl)
        logout()
    }

    @Test
    fun testGoogleLogOut () {
        googleLogin()
        logout()
        val actualUrl = driver.currentUrl

        Assertions.assertEquals("$BASE_URL/", actualUrl)
    }

    @Test
    fun testGithubLogOut () {
        githubLogin()
        logout()
        val actualUrl = driver.currentUrl

        Assertions.assertEquals("$BASE_URL/", actualUrl)
    }

    @Test
    fun logOutVisibleWhenBeingLoggedInWithGoogle() {
        googleLogin()
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_icon")))
        val logoutDisplayed = driver.findElement(By.id("logout_icon")).isDisplayed

        Assertions.assertTrue(logoutDisplayed)
        logout()
    }

    @Test
    fun logOutVisibleWhenBeingLoggedInWithGithub() {
        githubLogin()
        val wait = WebDriverWait(driver, Duration.ofSeconds(1))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_icon")))
        val logoutDisplayed = driver.findElement(By.id("logout_icon")).isDisplayed

        Assertions.assertTrue(logoutDisplayed)
        logout()
    }

    @Test
    fun emptyCartWhenBeingLoggedIn() {
        googleLogin()
        val wait = WebDriverWait(driver, Duration.ofSeconds(2))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cart_icon")))
        val cartIcon = driver.findElement(By.id("cart_icon"))
        cartIcon.click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("empty_cart")))
        val emptyCartDisplayed = driver.findElement(By.id("empty_cart")).isDisplayed

        Assertions.assertTrue(emptyCartDisplayed)
    }

    @Test
    fun addToCartAndCleanCart() {
        googleLogin()
        val wait = WebDriverWait(driver, Duration.ofSeconds(3))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add_to_cart_icon_1")))
        val addToCart = driver.findElement(By.id("add_to_cart_icon_1"))
        addToCart.click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cart_icon")))
        val cartIcon = driver.findElement(By.id("cart_icon"))
        cartIcon.click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_cart_1")))
        val bookInCartDisplayed = driver.findElement(By.id("book_cart_1")).isDisplayed
        val bookTitle = driver.findElement(By.id("book_in_cart_title_1"))
        val bookAuthor = driver.findElement(By.id("book_in_cart_author_1"))
        val selectAmount = driver.findElement(By.id("amount"))
        val bookPrice = driver.findElement(By.id("book_in_cart_price_1"))

        Assertions.assertTrue(bookInCartDisplayed)
        Assertions.assertTrue(bookTitle.isDisplayed)
        Assertions.assertEquals(firstBookTitle, bookTitle.text)
        Assertions.assertTrue(bookAuthor.isDisplayed)
        Assertions.assertEquals(firstBookAuthor, bookAuthor.text)
        Assertions.assertTrue(selectAmount.isDisplayed)
        Assertions.assertTrue(bookPrice.isDisplayed)
        Assertions.assertEquals(actualFirstBookPrice, bookPrice.text)

        cleanCart()

        wait.until(ExpectedConditions.elementToBeClickable(By.id("empty_cart")))
        val emptyCartDisplayed = driver.findElement(By.id("empty_cart")).isDisplayed

        Assertions.assertTrue(emptyCartDisplayed)
        logout()
    }

    @Test
    fun addToCartAndRemoveFromCart() {
        googleLogin()
        val wait = WebDriverWait(driver, Duration.ofSeconds(3))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add_to_cart_icon_1")))
        val addToCart = driver.findElement(By.id("add_to_cart_icon_1"))
        addToCart.click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cart_icon")))
        val cartIcon = driver.findElement(By.id("cart_icon"))
        cartIcon.click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_cart_1")))
        val deleteFromCart = driver.findElement(By.id("delete_from_cart_1"))
        deleteFromCart.click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("empty_cart")))
        val emptyCartDisplayed = driver.findElement(By.id("empty_cart")).isDisplayed

        Assertions.assertTrue(emptyCartDisplayed)
        logout()
    }

    @Test
    fun addTwoDifferentBooksToCartAndRemove() {
        googleLogin()
        val wait = WebDriverWait(driver, Duration.ofSeconds(3))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add_to_cart_icon_1")))
        var addToCart = driver.findElement(By.id("add_to_cart_icon_1"))
        addToCart.click()
        addToCart = driver.findElement(By.id("add_to_cart_icon_2"))
        addToCart.click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cart_icon")))
        val cartIcon = driver.findElement(By.id("cart_icon"))
        cartIcon.click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_cart_1")))
        val firstBookInCartDisplayed = driver.findElement(By.id("book_cart_1")).isDisplayed
        val secondBookInCartDisplayed = driver.findElement(By.id("book_cart_2")).isDisplayed
        val firstBookPrice = driver.findElement(By.id("book_in_cart_price_1"))
        val secondBookPrice = driver.findElement(By.id("book_in_cart_price_2"))

        Assertions.assertTrue(firstBookInCartDisplayed)
        Assertions.assertTrue(secondBookInCartDisplayed)
        Assertions.assertEquals(actualFirstBookPrice, firstBookPrice.text)
        Assertions.assertEquals(actualSecondBookPrice, secondBookPrice.text)

        cleanCart()

        wait.until(ExpectedConditions.elementToBeClickable(By.id("empty_cart")))
        val emptyCartDisplayed = driver.findElement(By.id("empty_cart")).isDisplayed

        Assertions.assertTrue(emptyCartDisplayed)
        logout()
    }

    @Test
    fun addTwoDifferentBooksTwiceToCartAndRemove() {
        googleLogin()
        val wait = WebDriverWait(driver, Duration.ofSeconds(3))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add_to_cart_icon_1")))
        var addToCart = driver.findElement(By.id("add_to_cart_icon_1"))
        addToCart.click()
        addToCart.click()
        addToCart = driver.findElement(By.id("add_to_cart_icon_2"))
        addToCart.click()
        addToCart.click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cart_icon")))
        val cartIcon = driver.findElement(By.id("cart_icon"))
        cartIcon.click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_cart_1")))
        val firstBookInCartDisplayed = driver.findElement(By.id("book_cart_1")).isDisplayed
        val secondBookInCartDisplayed = driver.findElement(By.id("book_cart_2")).isDisplayed
        val firstBookPrice = driver.findElement(By.id("book_in_cart_price_1"))
        val secondBookPrice = driver.findElement(By.id("book_in_cart_price_2"))

        Assertions.assertTrue(firstBookInCartDisplayed)
        Assertions.assertTrue(secondBookInCartDisplayed)
        Assertions.assertEquals("23.18 zł", firstBookPrice.text)
        Assertions.assertEquals("40.60 zł", secondBookPrice.text)

        cleanCart()

        wait.until(ExpectedConditions.elementToBeClickable(By.id("empty_cart")))
        val emptyCartDisplayed = driver.findElement(By.id("empty_cart")).isDisplayed

        Assertions.assertTrue(emptyCartDisplayed)
        logout()
    }

    @Test
    fun addToCartFromInfoPage() {
        googleLogin()
        val wait = WebDriverWait(driver, Duration.ofSeconds(3))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_info_icon_1")))

        val bookInfo = driver.findElement(By.id("book_info_icon_1"))
        bookInfo.click()

        wait.until(ExpectedConditions.elementToBeClickable(By.id("title_header_1")))
        val addToCartButton = driver.findElement(By.id("book_page_add_to_cart_1"))
        addToCartButton.click()
        val cartIcon = driver.findElement(By.id("cart_icon"))
        cartIcon.click()

        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_cart_1")))
        val bookInCartDisplayed = driver.findElement(By.id("book_cart_1")).isDisplayed
        val bookTitle = driver.findElement(By.id("book_in_cart_title_1"))
        val bookAuthor = driver.findElement(By.id("book_in_cart_author_1"))
        val selectAmount = driver.findElement(By.id("amount"))
        val bookPrice = driver.findElement(By.id("book_in_cart_price_1"))

        Assertions.assertTrue(bookInCartDisplayed)
        Assertions.assertTrue(bookTitle.isDisplayed)
        Assertions.assertEquals(firstBookTitle, bookTitle.text)
        Assertions.assertTrue(bookAuthor.isDisplayed)
        Assertions.assertEquals(firstBookAuthor, bookAuthor.text)
        Assertions.assertTrue(selectAmount.isDisplayed)
        Assertions.assertTrue(bookPrice.isDisplayed)
        Assertions.assertEquals(actualFirstBookPrice, bookPrice.text)

        cleanCart()

        wait.until(ExpectedConditions.elementToBeClickable(By.id("empty_cart")))
        val emptyCartDisplayed = driver.findElement(By.id("empty_cart")).isDisplayed
        Assertions.assertTrue(emptyCartDisplayed)
        logout()
    }

    @Test
    fun removeOnlyOneBookFromCart() {
        googleLogin()
        val wait = WebDriverWait(driver, Duration.ofSeconds(3))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add_to_cart_icon_1")))
        var addToCart = driver.findElement(By.id("add_to_cart_icon_1"))
        addToCart.click()
        addToCart = driver.findElement(By.id("add_to_cart_icon_2"))
        addToCart.click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cart_icon")))
        val cartIcon = driver.findElement(By.id("cart_icon"))
        cartIcon.click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_cart_1")))
        val firstBookInCartDisplayed = driver.findElement(By.id("book_cart_1")).isDisplayed
        var secondBookInCartDisplayed = driver.findElement(By.id("book_cart_2")).isDisplayed
        val firstBookPrice = driver.findElement(By.id("book_in_cart_price_1"))
        var secondBookPrice = driver.findElement(By.id("book_in_cart_price_2"))

        Assertions.assertTrue(firstBookInCartDisplayed)
        Assertions.assertTrue(secondBookInCartDisplayed)
        Assertions.assertEquals(actualFirstBookPrice, firstBookPrice.text)
        Assertions.assertEquals(actualSecondBookPrice, secondBookPrice.text)

        val deleteFromCart = driver.findElement(By.id("delete_from_cart_1"))
        deleteFromCart.click()
        secondBookInCartDisplayed = driver.findElement(By.id("book_cart_2")).isDisplayed
        secondBookPrice = driver.findElement(By.id("book_in_cart_price_2"))

        Assertions.assertTrue(secondBookInCartDisplayed)
        Assertions.assertEquals(actualSecondBookPrice, secondBookPrice.text)

        cleanCart()

        wait.until(ExpectedConditions.elementToBeClickable(By.id("empty_cart")))
        val emptyCartDisplayed = driver.findElement(By.id("empty_cart")).isDisplayed
        Assertions.assertTrue(emptyCartDisplayed)
        logout()
    }

    @Test
    fun alertDisplayedWhenBookAddedFromBookPage() {
        googleLogin()
        val wait = WebDriverWait(driver, Duration.ofSeconds(3))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_info_icon_1")))

        val bookInfo = driver.findElement(By.id("book_info_icon_1"))
        bookInfo.click()

        wait.until(ExpectedConditions.elementToBeClickable(By.id("title_header_1")))
        val addToCartButton = driver.findElement(By.id("book_page_add_to_cart_1"))
        addToCartButton.click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_added")))
        val bookAddedVisible = driver.findElement(By.id("book_added")).isDisplayed

        Assertions.assertTrue(bookAddedVisible)
        cleanCart()
    }

    @Test
    fun alertNotDisplayedWhenBookNotAddedFromBookPage() {
        googleLogin()
        val wait = WebDriverWait(driver, Duration.ofSeconds(3))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_info_icon_1")))

        val bookInfo = driver.findElement(By.id("book_info_icon_1"))
        bookInfo.click()

        wait.until(ExpectedConditions.elementToBeClickable(By.id("title_header_1")))
        val bookAddedVisible = driver.findElement(By.id("book_added")).isDisplayed

        Assertions.assertFalse(bookAddedVisible)
    }

    @Test
    fun cartIsSavedWhenLoggedInWithGoogle() {
        googleLogin()
        val wait = WebDriverWait(driver, Duration.ofSeconds(3))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_info_icon_1")))

        val bookInfo = driver.findElement(By.id("book_info_icon_1"))
        bookInfo.click()

        wait.until(ExpectedConditions.elementToBeClickable(By.id("title_header_1")))
        val addToCartButton = driver.findElement(By.id("book_page_add_to_cart_1"))
        addToCartButton.click()
        var cartIcon = driver.findElement(By.id("cart_icon"))
        cartIcon.click()

        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_cart_1")))
        var bookInCartDisplayed = driver.findElement(By.id("book_cart_1")).isDisplayed
        var bookTitle = driver.findElement(By.id("book_in_cart_title_1"))
        var bookAuthor = driver.findElement(By.id("book_in_cart_author_1"))
        var selectAmount = driver.findElement(By.id("amount"))
        var bookPrice = driver.findElement(By.id("book_in_cart_price_1"))

        Assertions.assertTrue(bookInCartDisplayed)
        Assertions.assertTrue(bookTitle.isDisplayed)
        Assertions.assertEquals(firstBookTitle, bookTitle.text)
        Assertions.assertTrue(bookAuthor.isDisplayed)
        Assertions.assertEquals(firstBookAuthor, bookAuthor.text)
        Assertions.assertTrue(selectAmount.isDisplayed)
        Assertions.assertTrue(bookPrice.isDisplayed)
        Assertions.assertEquals(actualFirstBookPrice, bookPrice.text)

        logout()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login_icon")))
        driver.findElement(By.id("login_icon")).click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("google_button")))
        driver.findElement(By.id("google_button")).click()

        cartIcon = driver.findElement(By.id("cart_icon"))
        cartIcon.click()

        wait.until(ExpectedConditions.elementToBeClickable(By.id("book_cart_1")))
        bookInCartDisplayed = driver.findElement(By.id("book_cart_1")).isDisplayed
        bookTitle = driver.findElement(By.id("book_in_cart_title_1"))
        bookAuthor = driver.findElement(By.id("book_in_cart_author_1"))
        selectAmount = driver.findElement(By.id("amount"))
        bookPrice = driver.findElement(By.id("book_in_cart_price_1"))

        Assertions.assertTrue(bookInCartDisplayed)
        Assertions.assertTrue(bookTitle.isDisplayed)
        Assertions.assertEquals(firstBookTitle, bookTitle.text)
        Assertions.assertTrue(bookAuthor.isDisplayed)
        Assertions.assertEquals(firstBookAuthor, bookAuthor.text)
        Assertions.assertTrue(selectAmount.isDisplayed)
        Assertions.assertTrue(bookPrice.isDisplayed)
        Assertions.assertEquals(actualFirstBookPrice, bookPrice.text)

        cleanCart()
        logout()
    }

    private fun googleLogin() {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(3))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login_icon")))
        driver.findElement(By.id("login_icon")).click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("google_button")))
        driver.findElement(By.id("google_button")).click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("identifierId")))
        driver.findElement(By.id("identifierId")).sendKeys("ebiznes.bookshop.test@gmail.com")
        driver.findElement(By.cssSelector("#identifierNext > div > button")).click()
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input")))
        driver.findElement(By.cssSelector("#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input")).sendKeys("BookShop1")
        driver.findElement(By.cssSelector("#passwordNext > div > button")).click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu")))
    }

    private fun githubLogin() {
        driver.get(BASE_URL)
        val wait = WebDriverWait(driver, Duration.ofSeconds(3))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login_icon")))
        driver.findElement(By.id("login_icon")).click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("github_button")))
        driver.findElement(By.id("github_button")).click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login_field")))
        driver.findElement(By.id("login_field")).sendKeys("ebiznes.bookshop.test@gmail.com")
        driver.findElement(By.id("password")).sendKeys("BookShop1")
        driver.findElement(By.cssSelector("#login > div.auth-form-body.mt-3 > form > div > input.btn.btn-primary.btn-block.js-sign-in-button")).click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu")))
    }

    private fun logout() {
        driver.findElement(By.id("logout_icon")).click()
        val wait = WebDriverWait(driver, Duration.ofSeconds(4))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_button")))
        driver.findElement(By.id("logout_button")).click()
    }

    private fun cleanCart() {
        val wait = WebDriverWait(driver, Duration.ofSeconds(3))
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cart_icon")))
        val cartIcon = driver.findElement(By.id("cart_icon"))
        cartIcon.click()
        wait.until(ExpectedConditions.elementToBeClickable(By.id("clean_cart")))
        val cleanCart = driver.findElement(By.id("clean_cart"))
        cleanCart.click()
    }

}