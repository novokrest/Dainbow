package org.oneuse.dainbow.goals;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class GoalControllerTest {
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                                                  MediaType.APPLICATION_JSON.getSubtype(),
                                                  Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter converter;

    private List<Goal> goalList = new ArrayList<>();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private GoalRepository goalRepository;

    public GoalControllerTest() { }

    private GoalControllerTest(WebApplicationContext webApplicationContext,
                               GoalRepository goalRepository) {
        this.webApplicationContext = webApplicationContext;
        this.goalRepository = goalRepository;
    }

    @Autowired
    public void setConverters(HttpMessageConverter<?>[] converters) {
        this.converter = Arrays.asList(converters)
                               .stream()
                               .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                               .findAny()
                               .orElse(null);
    }

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        goalRepository.deleteAll();

        goalList.add(goalRepository.save(GoalFactory.createForTest(1)));
        goalList.add(goalRepository.save(GoalFactory.createForTest(2)));
        goalList.add(goalRepository.save(GoalFactory.createForTest(3)));
    }

    @Test
    public void testGoalNotFound() throws Exception {
        mockMvc.perform(get("/api/goals/" + getNonExistentGoalId()))
               .andExpect(status().isNotFound());
    }

    @Test
    public void testReadSingleGoal() throws Exception {
        Goal goal = goalList.get(0);
        mockMvc.perform(get("/api/goals/" + goal.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(Long.valueOf(goal.getId()).intValue())))
                .andExpect(jsonPath("$.title", is(goal.getTitle())));
    }

    @Test
    public void testReadAllGoals() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/goals"))
                                             .andExpect(status().isOk())
                                             .andExpect(content().contentType(contentType));
        expect(resultActions, goalList);
    }

    private ResultActions expect(ResultActions resultActions, List<Goal> goalList) throws Exception {
        resultActions = resultActions.andExpect(jsonPath("$", hasSize(goalList.size())));
        for (int i = 0, count = goalList.size(); i < count; ++i) {
            resultActions = expect(resultActions, goalList.get(i), i);
        }
        return resultActions;
    }

    @Test
    public void testCreateGoal() throws Exception {
        Goal goal = new Goal("test-create-goal");
        String goalJson = json(goal);

        mockMvc.perform(post("/api/goals")
                .contentType(contentType)
                .content(goalJson))
                .andExpect(status().isCreated());
    }

    private ResultActions expect(ResultActions resultActions, Goal goal, int number) throws Exception {
        String goalJsonPath = "$[" + number + "]";
        return resultActions.andExpect(jsonPath(goalJsonPath + ".id", is(Long.valueOf(goal.getId()).intValue())))
                            .andExpect(jsonPath(goalJsonPath + ".title", is(goal.getTitle())));
    }

    private long getNonExistentGoalId() {
        Set<Long> ids = Sets.newHashSet(goalList.stream().map(g -> g.getId()).collect(Collectors.toList()));
        int foundId = 0;
        while (ids.contains(foundId)) {
            ++foundId;
        }
        return foundId;
    }

    private String json(Object o) throws IOException {
        MockHttpOutputMessage message = new MockHttpOutputMessage();
        converter.write(o, contentType, message);
        return message.getBodyAsString();
    }
}
