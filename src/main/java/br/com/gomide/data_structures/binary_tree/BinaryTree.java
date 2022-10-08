package br.com.gomide.data_structures.binary_tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> implements IBinaryTree<T> {
	private Node<T> raiz = null;
	
	


	@Override
	public Node<T> createTree(T element) {
		this.raiz = new Node<T>(element);
		return this.raiz;
	}

	@Override
	public Node<T> createTree(T[] elements) {
		for(int i = 0; i < elements.length; i++) {
			if( i == 0) {
				createTree(elements[i]);
			} else {
				insert(this.raiz, elements[i]);
			}
		}
		return this.raiz;
	}

	@Override
	public Integer degree(Node<T> rootNode, T nodeElement) {
	    System.out.println(verifica(rootNode, nodeElement));
	    Integer level = 0;
		if(rootNode == null || nodeElement == null) return null;
		Node<T> left = rootNode.getLeft();
		Node<T> right = rootNode.getRight();
		
		if(rootNode.getValue() == nodeElement) {
		    if ( rootNode.getLeft() != null ) {
		        level+=1;
		    }
		    if ( rootNode.getRight() != null ) {
                level+=1;
            }
		    
		    return level;
		}
		if(left != null) {
		    level += degree(rootNode.getLeft(), nodeElement);
		}
		
		if (right != null) {
		    level += degree(rootNode.getRight(), nodeElement);
		}
		return level;
	}
	
	private boolean verifica(Node<T> rootNode, T nodeElement) {
	    boolean ver = false;
	    if(rootNode == null || nodeElement == null) return false;
        Node<T> left = rootNode.getLeft();
        Node<T> right = rootNode.getRight();
        
        if(rootNode.getValue() == nodeElement) {
            ver = true;
            return ver;
        }
        if(left != null) {
            ver = verifica(rootNode.getLeft(), nodeElement);
        }
        
        if (right != null) {
            ver = verifica(rootNode.getRight(), nodeElement);
        }
        return ver;
	}

	@Override
	public void insert(Node<T> rootNode, T element) {
		Node<T> newNode = new Node<T>(element);
		// insere o rootNode na raiz se a raiz estiver vazia
		if(this.raiz == null) {
			this.raiz = rootNode;
			insert(rootNode, element);
		}
		else {
			Node<T> nowNode = this.raiz;
			while(true) { 
				if( newNode.getValue().compareTo(nowNode.getValue()) == -1 ) {
					if (nowNode.getLeft()!=null) { 
						nowNode = nowNode.getLeft();
					} else {
						nowNode.setLeft(newNode); break;
					}
				}
				else if (newNode.getValue().compareTo(nowNode.getValue()) == 1){
					if(nowNode.getRight() != null) {
						nowNode = nowNode.getRight();
					} else {
						nowNode.setRight(newNode);break;
					}
				}
				// neste caso o valor de inserção está se repetindo 
				else {
					break;
				}
			}
		}
	}

	@Override
	public boolean remove(Node<T> rootNode, T nodeElement) {
		
		
		
		return false;
	}
	
	@Override
	public Node<T> getFather(Node<T> rootNode, T nodeElement) {
		if( rootNode == null)
		{
			return null;
		}
		if (rootNode.getLeft() != null && rootNode.getLeft().getValue() == nodeElement) {
			return rootNode;
		}
		if(rootNode.getRight() != null && rootNode.getRight().getValue() == nodeElement) {
			return rootNode;
		}
		if(rootNode.getValue().compareTo(nodeElement) == 1 && rootNode!=null) {
			Node<T> newNode = getFather(rootNode.getLeft(), nodeElement);
			if(newNode != null) return newNode;
		}
		if(rootNode.getValue().compareTo(nodeElement) == -1 && rootNode!=null) {
			Node<T> newNode = getFather(rootNode.getRight(), nodeElement);
			if(newNode != null) return newNode;
		}
		return null;
	}
	

	@Override
	public Node<T> getBrother(Node<T> rootNode, T nodeElement) {
		if( rootNode.getValue().compareTo(nodeElement) == 0 || 
			rootNode.getValue().compareTo(this.raiz.getValue()) != 0 ||
			rootNode == null) return null;

		Node<T> nodeFather = getFather(rootNode, nodeElement);
			if( nodeFather == null || nodeFather.getLeft() == null
				|| nodeFather.getRight() == null) return null;
			
			else {
				if (nodeFather.getLeft().getValue().compareTo(nodeElement)==0) {
					return nodeFather.getRight();
				}
				else {
					return nodeFather.getLeft();
				}
			}
}

	@Override
	public Node<T> getByElement(Node<T> rootNode, T element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer calculateTreeDepth(Node<T> rootNode) {
		Integer aux = 0;
		Node<T> nodeLeft = rootNode.getLeft();
		Node<T> nodeRight = rootNode.getRight();

		if (nodeLeft != null) {
			Integer aux2 = calculateTreeDepth(rootNode.getLeft());
			if( aux > aux2) aux = aux;
			else aux = aux2;
		}

		if (nodeRight != null) {
			Integer aux2 = calculateTreeDepth(rootNode.getRight());
			if( aux > aux2) aux = aux;
			else aux = aux2;
		}
		//se for nó folha retornar 0
		
		if (nodeLeft == null && nodeRight == null) return 0;
		else { aux ++;
				return aux;
		}
	}

	@Override
	public Integer calculateNodeLevel(Node<T> rootNode, T nodeElement) {
		int level =-1;
		if(rootNode == null || nodeElement == null) {return null;} 
		else if(rootNode.getValue().compareTo(nodeElement) == 0) {
			return 0;
		}
		Node<T> nowNode = new Node<T>(nodeElement);
		while( true ) {
			if(nowNode == null) break;
			nowNode = getFather(rootNode, nowNode.getValue());
			level += 1;
			
		}
			return level;
	}
	
	public Node<T> getRaiz() {
		return raiz;
	}
	

	@Override
	public String toString(Node<T> rootNode) {
		String stringTree = "";
		String root ="";
		if(rootNode.getValue().compareTo(this.raiz.getValue()) == 0 &&
				getFather(rootNode, rootNode.getValue() )== null) {
			root = "root:";
		}
		stringTree += root + rootNode.getValue().toString() + " ";
		//nós pegando nós esquerdo e direito do parâmetro de entrada
		Node<T> right = rootNode.getRight();
		Node<T> left = rootNode.getLeft();
		
		if (right != null || left != null) {
			  stringTree += "("; 
		} 
		if (left != null) {
			stringTree += "left:" + toString(rootNode.getLeft());
		}
		if (right != null) {
			stringTree += "right:" + toString(rootNode.getRight());
		}
		if (right != null || left != null) {
			  stringTree += ")"; 
		} 
		return stringTree;
	}

}
