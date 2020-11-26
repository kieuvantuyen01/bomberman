package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;

import java.awt.*;

/**
 * Hiển thị thông điệp
 */
public class Message extends Entity {

	protected String _message;
	protected int _duration;
	protected Color _color;
	protected int _size;

	/**
	 * Hiển thị message khi tiêu diệt được Enemy ví dụ (+100)
	 * @param message

	 * @param duration
	 * @param color
	 * @param size
	 */
	public Message(String message, Coordinates pixel, int duration, Color color, int size) {
		super(pixel);
		_message = message;
		_duration = duration * 60; //seconds
		_color = color;
		_size = size;
	}

	public int getDuration() {
		return _duration;
	}

	public void setDuration(int _duration) {
		this._duration = _duration;
	}

	public String getMessage() {
		return _message;
	}

	public Color getColor() {
		return _color;
	}

	public int getSize() {
		return _size;
	}

	public boolean collide(Entity e) {
		return true;
	}
	
	
}
