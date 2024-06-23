package nature.calculadoraco22.Dto;

import java.util.List;

public class UserDto {
    private String username;
    private List<EmissionDto> emissions;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<EmissionDto> getEmissions() {
        return emissions;
    }

    public void setEmissions(List<EmissionDto> emissions) {
        this.emissions = emissions;
    }
}