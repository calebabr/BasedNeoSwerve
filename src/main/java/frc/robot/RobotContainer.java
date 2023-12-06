package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Swerve;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public final Joystick leftJoy;
  public final Joystick rightJoy;
  public final XboxController xbox;

  public final Swerve swerve;


  public RobotContainer() {
    leftJoy = new Joystick(Constants.kControls.LEFT_JOY_ID);
    rightJoy = new Joystick(Constants.kControls.RIGHT_JOY_ID);
    xbox = new XboxController(2);


    swerve = new Swerve();


    // Configure button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    swerve.setDefaultCommand(swerve.drive(
      () -> -Constants.kControls.Y_DRIVE_LIMITER.calculate(leftJoy.getY()), 
      () -> -Constants.kControls.X_DRIVE_LIMITER.calculate(-leftJoy.getX()),  
      () -> -Constants.kControls.THETA_DRIVE_LIMITER.calculate(rightJoy.getX()),
      true,
      false
      ));

    // new JoystickButton(xbox, Constants.kControls.GYRO_RESET_BUTTON)
    //   .onTrue(swerve.zeroGyroCommand());
    
    // new JoystickButton(xbox, Constants.kControls.GYRO_RESET_BUTTON).onTrue(swerve.zeroGyroCommand());
  }
}
