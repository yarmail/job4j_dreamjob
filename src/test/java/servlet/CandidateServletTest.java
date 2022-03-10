package servlet;

import model.Candidate;
import org.junit.Test;
import store.DbStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CandidateServletTest {
    @Test
    public void whenCreatePost() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("name of new Candidate");
        when(req.getParameter("description")).thenReturn("d");
        new CandidateServlet().doPost(req, resp);
        Candidate candidate = DbStore.instOf().findByNameCandidate("name of new Candidate");
        assertThat(candidate, notNullValue());
    }
}