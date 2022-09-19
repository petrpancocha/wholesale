package com.petrpancocha.wholesale.repository;

import com.petrpancocha.wholesale.model.Task;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TaskMyBatisRepository {
    @Select("SELECT * FROM tasks")
    List<Task> findAll();

    @Select("SELECT * FROM tasks WHERE id = #{id}")
    Task findById(long id);

    @Select("SELECT * FROM tasks WHERE acquired_by = #{acquiredBy}")
    List<Task> findByAcquiredBy(long acquiredBy);

    @SelectProvider(SelectTasksLikeUserNoteProvider.class)
    List<Task> findByLikeUserNote(String userNote);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO tasks(user_note, task_data, acquired_by, created_by) VALUES (#{userNote}, #{taskData}, #{acquiredBy}, #{createdBy})")
    int insert(Task task);

    @Delete("DELETE FROM tasks WHERE id = #{id}")
    int deleteById(long id);

    @Update("UPDATE tasks SET user_note=#{userNote}, task_data=#{taskData}, acquired_by=#{acquiredBy} where id=#{id}")
    int update(Task task);
}