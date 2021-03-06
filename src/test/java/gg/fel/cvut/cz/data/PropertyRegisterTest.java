package gg.fel.cvut.cz.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import gg.fel.cvut.cz.data.properties.DynamicPropertyRegister;
import gg.fel.cvut.cz.data.properties.Property;
import org.junit.Test;

public class PropertyRegisterTest {

  @Test
  public void testPropertyRegister() {
    String value = "Hello";
    DynamicPropertyRegister<String, Property<String>> dynamicPropertyRegister = new DynamicPropertyRegister<String, Property<String>>(
        Property::new);
    assertTrue(!dynamicPropertyRegister.getLatestValue().isPresent());
    dynamicPropertyRegister.addProperty(value, 10);
    assertTrue(dynamicPropertyRegister.getLatestValue().isPresent());
    assertTrue(!dynamicPropertyRegister.getValueInFrame(9).isPresent());
    assertTrue(dynamicPropertyRegister.getValueInFrame(10).isPresent());
    dynamicPropertyRegister.addProperty(value, 12);
    assertTrue(dynamicPropertyRegister.getValueInFrame(12).isPresent());
    assertEquals(dynamicPropertyRegister.getValueInFrame(12).get(), value);
    assertTrue(dynamicPropertyRegister.getValueInFrame(14).isPresent());
    assertEquals(dynamicPropertyRegister.getValueInFrame(14).get(), value);
  }

}
