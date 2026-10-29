import com.codeborne.selenide.Configuration;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import Pageobject.BasePage;
import java.io.IOException;
import static Browser.Browser.initDriver;
import static com.codeborne.selenide.Selenide.open;


@Epic("Burger constructor")
public class ConstructorBurgerTest {
    private BasePage basePage;


    @Before
    public void setUp() throws IOException {
        //initialization our Browser
        initDriver();
        Configuration.timeout = 6000;
        //create an instance of BasePage class
        basePage = open(BasePage.BASE_URL, BasePage.class);
    }


    @Test
    @DisplayName("Check if the bun ingredients 'Булки' current")
    public void shouldActivateBunsTab() {
        basePage.selectTabAndAssertActive("Соусы");
        basePage.selectTabAndAssertActive("Булки");
    }

    @Test
    @DisplayName("Check if the sauce ingredient 'Соусы' current")
    public void shouldActivateSaucesTab() {
        basePage.selectTabAndAssertActive("Соусы");
    }

    @Test
    @DisplayName("Check if the filling ingredient 'Начинки' current")
    public void checkIfFillingIngredientIsCurrent() {
        basePage.selectTabAndAssertActive("Начинки");
    }
}