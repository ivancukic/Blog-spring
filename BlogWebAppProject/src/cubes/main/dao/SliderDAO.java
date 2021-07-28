package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Slider;

public interface SliderDAO {
	
	public List<Slider> getSliderList(); // SLIDER LIST
	
	public void saveSlider(Slider slider); // SAVE SLIDER
	
	public Slider getSlider(int id); // UPDATE SLIDER
	
	public void deleteSlider(int id); // DELETE SLIDER
	
	public List<Slider> getSliderOnIndexPage(); // LIST OF SLIDER ON INDEX PAGE

}
