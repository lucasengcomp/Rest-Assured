package rest;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class MatchersTest {

    @Test
    public void comparaDuasStrings() {
        Assert.assertThat("Lucas", is("Lucas"));
    }
    @Test
    public void comparaDoisNumeros() {
        Assert.assertThat(73, is(73));
    }

    @Test
    public void verificaTipoNumeroInteger() {
        Assert.assertThat(73, Matchers.isA(Integer.class));
    }

    @Test
    public void verificaTipoNumeroDouble() {
        Assert.assertThat(73D, Matchers.isA(Double.class));
    }

    @Test
    public void verificaMaiorEntreDoisNumeros() {
        Assert.assertThat(73, Matchers.greaterThan(71));
    }

    @Test
    public void verificaMenorEntreDoisNumeros() {
        Assert.assertThat(73, Matchers.lessThan(74));
    }

    @Test
    public void verificaTamanhoDaLista() {
        List<Integer> primos = Arrays.asList(1,3,5,7,11);
        Assert.assertThat(primos, Matchers.hasSize(5));
    }

    @Test
    public void verificaSeContemNumerosNaOrdemQueFoiDeclaradoNaLista() {
        List<Integer> primos = Arrays.asList(1,3,5,7,11);
        Assert.assertThat(primos, Matchers.contains(1,3,5,7,11));
    }

    @Test
    public void verificaSeContemNumerosSemAOrdemQueFoiDeclaradoNaLista() {
        List<Integer> primos = Arrays.asList(1,3,5,7,11);
        Assert.assertThat(primos, Matchers.containsInAnyOrder(1,5,11,7,3));
    }

    @Test
    public void verificaSeContemUmNumeroDeclaradoNaLista() {
        List<Integer> primos = Arrays.asList(1,3,5,7,11);
        Assert.assertThat(primos, Matchers.hasItem(7));
    }

    @Test
    public void verificaSeContemNumerosDeclaradoNaLista() {
        List<Integer> primos = Arrays.asList(1,3,5,7,11);
        Assert.assertThat(primos, Matchers.hasItems(7,3));
    }

    @Test
    public void verificaSeUmNomeEDiferenteDeOutro() {
        Assert.assertThat("Lucas", is(not("Brian May")));
        Assert.assertThat("Lucas", not("Brian May"));
    }

    @Test
    public void verificaCondicaoOUSeNomeEAlgumDosDemais() {
        Assert.assertThat("Lucas", anyOf(is("Ranger vermelho"), is("Lucas"), is("Batman")));
    }

    @Test
    public void verificaCondicaoEComNomesPassados() {
        Assert.assertThat("Caroliny", allOf(startsWith("Ca"), endsWith("liny"), containsString("ro")));
    }
}
