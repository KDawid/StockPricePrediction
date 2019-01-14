package elte.softwaretechnology.stockprices.parsing;

import elte.softwaretechnology.stockprices.data.model.Article;
import elte.softwaretechnology.stockprices.data.model.Meta;
import elte.softwaretechnology.stockprices.parsers.implementations.NewYorkTimesDataMessageParser;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static java.lang.Math.abs;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class NewYorkTimeMessageParserTest {
    @Test
    public void testResponseParsing() throws Exception {
			List<Article> articles = new NewYorkTimesDataMessageParser().parseResponeData(readInput("new_york_times_response_example.txt"));
        assertThat(articles, hasSize(5));
        Article firstArticle = articles.get(0);
        assertEquals("https://www.nytimes.com/2015/02/20/arts/spare-times-for-children-for-feb-20-26.html", firstArticle.getUrl());
        assertEquals("A selected guide to cultural events and activities for children, teenagers and families in New York City.", firstArticle.getSnippet());
        assertTrue(abs(firstArticle.getScore() - 2.5064845) < 0.001);
        assertEquals("Spare Times for Children for Feb. 20-26", firstArticle.getTitle());
        assertEquals(3674, (long) firstArticle.getWordCount());
        assertEquals("2015-02-20T00:00", firstArticle.getDateOfPublication().toString());
        assertThat(firstArticle.getKeyWords(), hasSize(2));
        assertEquals("Children and Childhood", firstArticle.getKeyWords().get(0).getKeyWord());
        assertEquals("Culture (Arts)", firstArticle.getKeyWords().get(1).getKeyWord());

        assertEquals("Article{dateOfPublication='2015-02-20T00:00', title=The Times Company Announces Board Nominees With Web Credentials}", articles.get(1).toString());
        assertEquals("Article{dateOfPublication='2015-02-20T00:00', title=Apple Is Forming an Auto Team}", articles.get(2).toString());
        assertEquals("Article{dateOfPublication='2015-02-20T20:08:34', title=Now Boarding}", articles.get(3).toString());
        assertEquals("Article{dateOfPublication='2015-02-20T22:00:23', title=Animated Character Who&#8217;s Five Apples Tall}", articles.get(4).toString());
    }

    @Test
    public void testMetaParsing() throws Exception {
			Meta meta = new NewYorkTimesDataMessageParser().parseResponeMeta(readInput("new_york_times_response_example.txt"));
        assertEquals(5, (long) meta.getHits());
        assertEquals(0, (long) meta.getOffset());
    }

    private String readInput(String fileName) throws IOException {
        return new String(Files.readAllBytes(new File(getClass().getResource(fileName).getFile()).toPath()));
    }
}
