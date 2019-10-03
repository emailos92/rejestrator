/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;

/**
 *
 * @author Arkita
 */
public class CyclingSpinnerListModel extends SpinnerListModel {
     @SuppressWarnings("unchecked")
  Object firstValue, lastValue;
  SpinnerModel linkedModel = null;

  public CyclingSpinnerListModel(Object[] values) {   
    super(values);
    firstValue = values[1];
    lastValue = values[values.length - 1];
  }

  public void setLinkedModel(SpinnerModel linkedModel) {
    this.linkedModel = linkedModel;
  }
  
  @Override
  public Object getNextValue() {
    Object value = super.getNextValue();
    if (value == null) {
      value = firstValue;
      if (linkedModel != null) {
        linkedModel.setValue(linkedModel.getNextValue());
      }
    }
    return value;
  }

  @Override
  public Object getPreviousValue() {
    Object value = super.getPreviousValue();
    if (value.equals("")) {
      value = lastValue;
      if (linkedModel != null) {
        linkedModel.setValue(linkedModel.getPreviousValue());
      }
    }
    return value;
  }
}

