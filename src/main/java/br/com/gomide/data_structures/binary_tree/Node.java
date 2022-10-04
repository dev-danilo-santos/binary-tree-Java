package br.com.gomide.data_structures.binary_tree;

import java.util.Objects;

public class Node<T extends Comparable> {
	private T value;
	private Node<T> left;
	private Node<T> right;
	 
	
	
	public Node(T value, Node<T> left, Node<T> right) {
		super();
		this.value = value;
		this.left = left;
		this.right = right;
	}
	public Node(T value) {
		super();
		this.value = value;
		this.left = null;
		this.right = null;
	}
	
	
	
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public Node<T> getLeft() {
		return left;
	}
	public void setLeft(Node<T> left) {
		this.left = left;
	}
	public Node<T> getRight() {
		return right;
	}
	public void setRight(Node<T> right) {
		this.right = right;
	}
	

	

}
