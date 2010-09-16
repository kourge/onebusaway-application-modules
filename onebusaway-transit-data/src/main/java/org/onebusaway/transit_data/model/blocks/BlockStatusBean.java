package org.onebusaway.transit_data.model.blocks;

import java.io.Serializable;

import org.onebusaway.geospatial.model.CoordinatePoint;
import org.onebusaway.transit_data.model.StopBean;
import org.onebusaway.transit_data.model.trips.TripBean;

/**
 * Status of a particular block instance, including real-time location
 * information when available.
 * 
 * @author bdferris
 * @see BlockDetailsBean
 */
public final class BlockStatusBean implements Serializable {

  private static final long serialVersionUID = 1L;

  /****
   * These are fields that we can supply from schedule data
   ****/

  private long serviceDate;
  
  private double scheduledDistanceAlongBlock = Double.NaN;

  private double totalDistanceAlongBlock = Double.NaN;

  /****
   * These are fields that we can supply from schedule data, but also update
   * from real-time data when available
   ****/

  private boolean inService = false;
  
  private String status;

  private CoordinatePoint location;

  private TripBean activeTrip;

  private StopBean closestStop;

  /**
   * In seconds
   */
  private int closestStopTimeOffset;

  /****
   * These are fields that we can supply only from real-time data
   ****/

  private boolean predicted = false;

  private long lastUpdateTime;

  private double scheduleDeviation = Double.NaN;

  private double distanceAlongBlock = Double.NaN;

  private String vehicleId;

  public long getServiceDate() {
    return serviceDate;
  }

  public void setServiceDate(long serviceDate) {
    this.serviceDate = serviceDate;
  }

  /**
   * The vehicle's scheduled distance along the block.
   * 
   * @return distance, in meters
   */
  public double getScheduledDistanceAlongBlock() {
    return scheduledDistanceAlongBlock;
  }

  public void setScheduledDistanceAlongBlock(double scheduledDistanceAlongBlock) {
    this.scheduledDistanceAlongBlock = scheduledDistanceAlongBlock;
  }

  public double getTotalDistanceAlongBlock() {
    return totalDistanceAlongBlock;
  }

  public void setTotalDistanceAlongBlock(double totalDistanceAlongBlock) {
    this.totalDistanceAlongBlock = totalDistanceAlongBlock;
  }

  public boolean isInService() {
    return inService;
  }

  public void setInService(boolean inService) {
    this.inService = inService;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public CoordinatePoint getLocation() {
    return location;
  }

  public void setLocation(CoordinatePoint location) {
    this.location = location;
  }

  public TripBean getActiveTrip() {
    return activeTrip;
  }

  public void setActiveTrip(TripBean activeTrip) {
    this.activeTrip = activeTrip;
  }

  public StopBean getClosestStop() {
    return closestStop;
  }

  public void setClosestStop(StopBean closestStop) {
    this.closestStop = closestStop;
  }

  /**
   * The time offset, in seconds, from the closest stop to the current position
   * of the transit vehicle among the stop times of the current block. If the
   * number is positive, the stop is coming up. If negative, the stop has
   * already been passed.
   * 
   * @return time, in seconds
   */
  public int getClosestStopTimeOffset() {
    return closestStopTimeOffset;
  }

  /**
   * See description in {@link #getClosestStopTimeOffset()}.
   * 
   * @param closestStopTimeOffset the time offset from the closest stop, in
   *          seconds
   */
  public void setClosestStopTimeOffset(int closestStopTimeOffset) {
    this.closestStopTimeOffset = closestStopTimeOffset;
  }

  public boolean isPredicted() {
    return predicted;
  }

  public void setPredicted(boolean predicted) {
    this.predicted = predicted;
  }

  public void setLastUpdateTime(long lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

  /**
   * @return the time we last heard from the bus (Unix-time)
   */
  public long getLastUpdateTime() {
    return lastUpdateTime;
  }

  public boolean isScheduleDeviationSet() {
    return !Double.isNaN(scheduleDeviation);
  }

  public double getScheduleDeviation() {
    return scheduleDeviation;
  }

  public void setScheduleDeviation(double scheduleDeviation) {
    this.scheduleDeviation = scheduleDeviation;
  }

  /**
   * @return true if {@link #getDistanceAlongBlock()} has been set
   */
  public boolean isDistanceAlongBlockSet() {
    return !Double.isNaN(distanceAlongBlock);
  }

  /**
   * The vehicle's distance along the block.
   * 
   * @return distance, in meters
   */
  public double getDistanceAlongBlock() {
    return distanceAlongBlock;
  }

  /**
   * See description in {@link #getDistanceAlongBlock()}.
   * 
   * @param distanceAlongBlock
   */
  public void setDistanceAlongBlock(double distanceAlongBlock) {
    this.distanceAlongBlock = distanceAlongBlock;
  }

  public String getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
  }
}