// 1 - bibliotecas / imports
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; // biblioteca principal do Selenium
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver; // biblioteca do ChromeDriver (conversa com Chrome e Selenium)

// 2 - classe
public class ComprarPassagemTest {
    // 2.1 - Atributos
    private WebDriver driver; // objeto do Selenium
    
    // 2.2 - Funções e Métodos

    // Antes do teste
    @BeforeEach
    public void iniciar() {
        driver = new ChromeDriver(); // instanciar o objeto do Selenium como ChromeDriver
        driver.manage().window().maximize(); // maximiza a janela do browser
    }

    // Depois do teste
    @AfterEach
    public void finalizar() {
        driver.quit(); // destrói o objeto do Selenium
    }

    // Teste
    @Test
    public void comprarPassagem() {
        driver.get("https://www.blazedemo.com/"); // abre o site

        // Seleciona origem
        driver.findElement(By.name("fromPort")).click(); // clica no combo
        {
            WebElement dropdown = driver.findElement(By.name("fromPort")); // lista que abre pra baixo
            dropdown.findElement(By.xpath("//option[. = 'São Paolo']")).click();
        }

        // Seleciona destino
        driver.findElement(By.name("toPort")).click(); // clica no combo
        {
            WebElement dropdown = driver.findElement(By.name("toPort")); // lista que abre pra baixo
            dropdown.findElement(By.xpath("//option[. = 'London']")).click();
        }

        // Clica no botão "Find Flights"
       driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

       // TRANSIÇÃO DA PÁGINA
       
       // Verifica se foi realizado o login e a pesquisa dos vôos
       assertEquals("Flights from São Paolo to London:", 
        driver.findElement(By.cssSelector("h3")).getText());
       
       // Clica no botão "Choose This Flight"
       driver.findElement(By.cssSelector("tr:nth-child(2) .btn")).click();
    
       // TRANSIÇÃO DA PÁGINA

       assertEquals("Your flight from TLV to SFO has been reserved.", 
        driver.findElement(By.cssSelector("h2")).getText());
    }
}