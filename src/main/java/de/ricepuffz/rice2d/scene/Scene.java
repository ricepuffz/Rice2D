package de.ricepuffz.rice2d.scene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.ricepuffz.rice2d.References;
import de.ricepuffz.rice2d.scene.object.Sprite;

public class Scene
{
	private String name = "";
	
	List<SceneObject> objectList = null;
	
	//Map object names to index of objectList
	Map<String, Integer> objects = null;
	
	//Map layer names to relative position values
	Map<String, Integer> layers = null;
	
	//Map layer names to absolute position values
	Map<String, Float> layerZValues = null;
	
	//Map object names to layer names
	Map<String, String> objectLayers = null;
	
	List<String> layersSorted = null;
	
	
	public Scene(String name)
	{
		this.name = name;
		
		objectList = new ArrayList<SceneObject>();
		objects = new HashMap<String, Integer>();
		layers = new HashMap<String, Integer>();
		layerZValues = new HashMap<String, Float>();
		objectLayers = new HashMap<String, String>();
		layersSorted = new ArrayList<String>();
		
		addLayer("default", 0);
	}
	
	public void addObject(SceneObject object, String name)
	{
		if (objects.containsKey(name) || objectList.contains(object))
			System.err.println("SceneManager ERROR: Couldn't add object '" + name + "' to scene '" + this.name + "', objects already exists!");
		else
		{
			objectList.add(object);
			objects.put(name, objectList.indexOf(object));
			setLayer(name, "default");
			calculateLayerZValues();
		}
	}
	
	public void removeObject(String name)
	{
		SceneObject object = getObject(name);
		if (object instanceof Sprite && ((Sprite) object).usedModel() != null)
			References.resourceManager.removeModel(((Sprite) object).usedModel());
		
		objectList.set(objects.get(name), null);
		objects.remove(name);
		objectLayers.remove(name);
	}
	
	public SceneObject getObject(String name)
	{
		if (objects.containsKey(name))
			return objectList.get(objects.get(name));
		else
		{
			System.err.println("SceneManager ERROR: Couldn't get object '" + name + "' from scene +'" + this.name + "' because it doesn't exist! Returning null...");
			return null;
		}
	}
	
	public void addLayer(String name, int position)
	{
		if (layers.containsValue(position))
		{
			System.err.println("SceneManager ERROR: Couldn't add scene '" + name + "', because a scene with the same position already exists!");
		}
		layers.put(name, position);
		calculateLayerZValues();
		sortLayers();
	}
	
	private void calculateLayerZValues()
	{
		Map<Integer, String> layersReversed = new HashMap<Integer, String>();
		List<Integer> positions = new ArrayList<Integer>();
		
		for (String key : layers.keySet())
		{
			layersReversed.put(layers.get(key), key);
			positions.add(layers.get(key));
		}
		
		Collections.sort(positions);
		
		for (int i = 0; i < positions.size(); i++)
		{
			layerZValues.put(layersReversed.get(positions.get(i)), (float) i / positions.size());
		}
	}
	
	public String objectName(SceneObject object)
	{
		for (String name: objects.keySet())
		{
			if (objectList.get(objects.get(name)) == object)
				return name;
		}
		
		return null;
	}
	
	public Map<String, Float> zValues()
	{
		return layerZValues;
	}
	
	public void setLayer(String objectName, String layerName)
	{
		objectLayers.put(objectName, layerName);
		applyLayersToObjectZs();
	}
	
	private void applyLayersToObjectZs()
	{
		for (String objectName : objects.keySet())
		{
			SceneObject object = objectList.get(objects.get(objectName));
			if (object == null)
			{
				System.err.println("Scene ERROR: Couldn't apply z coordinate from layer to object '" + objectName + "' because the object doesn't exist!");
				return;
			}
			if (objectLayers.get(objectName) == null)
			{
				System.err.println("Scene ERROR: Couldn't apply z coordinate from layer to object '" + objectName + "' because the layer doesn't exist!");
				return;
			}
			object.getPosVector().z = layerZValues.get(objectLayers.get(objectName));
		}
	}
	
	public void sortLayers()
	{
		layersSorted = new ArrayList<String>();
		List<Integer> orderList = new ArrayList<Integer>();
		Map<Integer, String> reversedLayers = new HashMap<Integer, String>();
		
		for (String layer : layers.keySet())
		{
			int position = layers.get(layer);
			reversedLayers.put(position, layer);
			orderList.add(position);
		}
		
		Collections.sort(orderList);
		
		for (int position : orderList)
			layersSorted.add(reversedLayers.get(position));
	}
	
	public List<String> getObjectsFromLayer(String layer)
	{
		List<String> objectsFromLayer = new ArrayList<String>();
		
		for (String object : objects.keySet())
		{
			if (objectLayers.get(object).equals(layer))
				objectsFromLayer.add(object);
		}
		
		return objectsFromLayer;
	}
	
	public List<String> getLayersSorted()
	{
		return layersSorted;
	}
	
	public String getName() { return name; }
	public List<SceneObject> getObjects() { return objectList; }
}
