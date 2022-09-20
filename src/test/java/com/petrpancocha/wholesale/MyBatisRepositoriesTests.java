package com.petrpancocha.wholesale;

import com.petrpancocha.wholesale.model.Task;
import com.petrpancocha.wholesale.model.User;
import com.petrpancocha.wholesale.repository.TaskMyBatisRepository;
import com.petrpancocha.wholesale.repository.UserMyBatisRepository;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Note: tests are using initial set of data that are setup in src/main/resources/data.sql
 */
@MybatisTest
class MyBatisRepositoriesTests {

    @Autowired
    UserMyBatisRepository userRepository;

    @Autowired
    TaskMyBatisRepository taskRepository;

    @Test
    void testGetAllUsers() {
        List<User> users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(2);
    }

    @Test
    void testGetUnkownUser() {
        User user = userRepository.findById(-1);
        assertThat(user).isNull();
    }

    @Test
    void testGetUserById() {
        List<User> users = userRepository.findAll();
        assertThat(users).isNotEmpty();

        User referenceUser = users.get(0);

        User user = userRepository.findById(referenceUser.getId());
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(referenceUser.getId());
        assertThat(user.getLoginName()).isEqualTo(referenceUser.getLoginName());
        assertThat(user.getFirstName()).isEqualTo(referenceUser.getFirstName());
        assertThat(user.getLastName()).isEqualTo(referenceUser.getLastName());
    }

    @Test
    void testCRUDTasks() {
        // fetch tasks
        List<Task> tasks = taskRepository.findAll();
        assertThat(tasks).isEmpty();

        // fetch user whose ID will be used as task's acquiredBy and createdBy ID
        List<User> users = userRepository.findAll();
        assertThat(users).isNotEmpty();
        assertThat(users.size()).isEqualTo(2);

        User referenceUser = users.get(0);
        User referenceUser2 = users.get(1);

        // create new task
        Task taskToBeInserted = new Task();
        taskToBeInserted.setCreatedBy(referenceUser.getId());
        taskToBeInserted.setAcquiredBy(referenceUser.getId());
        taskToBeInserted.setTaskData("Task data");
        taskToBeInserted.setUserNote("User note");

        int count = taskRepository.insert(taskToBeInserted);
        assertThat(count).isEqualTo(1);

        // fetch tasks with new one
        tasks = taskRepository.findAll();
        assertThat(tasks.size()).isEqualTo(1);

        Task createdTask = tasks.get(0);
        assertTasks(createdTask, taskToBeInserted, false /* newTask does not have ID yet*/, true);

        // fetch task by unknown ID
        Task task = taskRepository.findById(-1);
        assertThat(task).isNull();

        // fetch task by existing ID
        task = taskRepository.findById(createdTask.getId());
        assertThat(task).isNotNull();
        assertTasks(task, createdTask, true, true);

        // fetch task "by like" non-matching userNote
        tasks = taskRepository.findByLikeUserNote("xyz");
        assertThat(tasks).isEmpty();

        // fetch task "by like" matching userNote
        tasks = taskRepository.findByLikeUserNote("ser no"); /* 'ser No' is like 'User Note' */
        assertThat(tasks.size()).isEqualTo(1);

        task = tasks.get(0);
        assertTasks(task, createdTask, true, true);

        // fetch task by unknown acquiredBy ID
        tasks = taskRepository.findByAcquiredBy(-1);
        assertThat(tasks).isEmpty();

        // fetch task by valid acquiredBy ID
        tasks = taskRepository.findByAcquiredBy(taskToBeInserted.getAcquiredBy());
        assertThat(tasks.size()).isEqualTo(1);

        task = tasks.get(0);
        assertTasks(task, createdTask, true, true);

        // update task
        Task taskToBeUpdated = new Task();
        taskToBeUpdated.setId(createdTask.getId());
        taskToBeUpdated.setAcquiredBy(referenceUser2.getId());
        taskToBeUpdated.setTaskData("Task data updated");
        taskToBeUpdated.setUserNote("User note updated");
        taskToBeUpdated.setCreatedBy(referenceUser2.getId()); // will not be updated

        count = taskRepository.update(taskToBeUpdated);
        assertThat(count).isEqualTo(1);

        // fetch updated task
        Task updatedTask = taskRepository.findById(createdTask.getId());
        assertThat(updatedTask).isNotNull();

        assertTasks(updatedTask, taskToBeUpdated, true, false /* createdBy is not updateable field -> do not check it here */);
        assertThat(updatedTask.getCreatedBy()).isNotEqualTo(taskToBeUpdated.getCreatedBy());  /* but check it here */

        // delete task
        count = taskRepository.deleteById(createdTask.getId());
        assertThat(count).isEqualTo(1);

        // check if tasks are removed
        tasks = taskRepository.findAll();
        assertThat(tasks).isEmpty();
    }

    private void assertTasks(Task actual, Task expected, boolean checkId, boolean checkCreatedBy) {
        if (checkId) {
            assertThat(actual.getId()).isEqualTo(expected.getId());
        }
        if (checkCreatedBy) {
            assertThat(actual.getCreatedBy()).isEqualTo(expected.getCreatedBy());
        }
        assertThat(actual.getAcquiredBy()).isEqualTo(expected.getAcquiredBy());
        assertThat(actual.getTaskData()).isEqualTo(expected.getTaskData());
        assertThat(actual.getUserNote()).isEqualTo(expected.getUserNote());
    }
}
