package pl.sda.tasklist.dto;

import lombok.Data;

import java.util.List;

@Data
public class TaskCategoryDto {
    private String name;
    private String description;
    private List<TaskDto> tasks;
}
