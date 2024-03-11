package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.AxisCamera;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static CTREConfigs ctreConfigs = new CTREConfigs();
  private Command m_autonomousCommand;
  public static AxisCamera limelightamp;
  public static AxisCamera limelightsource;
  public static RobotContainer robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // CameraServer.startAutomaticCapture();
    ctreConfigs = new CTREConfigs();
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
  

    limelightamp = CameraServer.addAxisCamera("limelightamp", "10.75.28.2");
    RobotContainer.limelightamp.cameraMode();
    NetworkTableInstance.getDefault().getTable("limelightamp").getEntry("stream").setNumber(2);
    limelightamp.setFPS(30);
    limelightamp.setResolution(1280, 720);

    limelightsource = CameraServer.addAxisCamera("limelightsource", "10.75.28.2");
    RobotContainer.limelightsource.cameraMode();
    NetworkTableInstance.getDefault().getTable("limelightsource").getEntry("stream").setNumber(2);
    limelightsource.setFPS(30);
    limelightsource.setResolution(1280, 720);

    robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();

    // RobotContainer.climb.updateDashboard();
    // RobotContainer.intakesupport.updateDashboard();
    // RobotContainer.sweeper.updateDashboard();
    RobotContainer.limelightamp.updateDashboard();
    RobotContainer.limelightsource.updateDashboard();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  // @Override
  // public void autonomousInit() {
  //   m_autonomousCommand = robotContainer.getAutonomousCommand();

  //   // schedule the autonomous command (example)
  //   if (m_autonomousCommand != null) {
  //     m_autonomousCommand.schedule();
  //   }
  // }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {


    
    
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    //m_robotContainer.elevator.resetElevatorEncoder();
    //m_robotContainer.wrist.resetWristEncoder();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}