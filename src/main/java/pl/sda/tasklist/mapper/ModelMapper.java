package pl.sda.tasklist.mapper;

import pl.sda.tasklist.dto.TaskDto;
import pl.sda.tasklist.model.TaskEntity;

public class ModelMapper {

    private ModelMapper(){

    }
    public TaskDto map(TaskEntity task){
        return new TaskDto(task.getUuid(),task.getName(),task.getDescription(), task.isDone(),
                task.getPriority(),task.getCategory());
    }




}
