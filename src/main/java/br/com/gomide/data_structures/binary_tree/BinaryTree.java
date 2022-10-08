package br.com.gomide.data_structures.binary_tree;

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
	    Integer level = 0;
		Node<T> left = rootNode.getLeft();
		Node<T> right = rootNode.getRight();
		
		if(rootNode.getValue() == nodeElement) {
		    if ( left != null ) {
		        level+=1;
		    }
		    if ( right != null ) {
                level+=1;
            }
		    
		    return level;
		}
		else {
		    
		    if(rootNode.getValue().compareTo(nodeElement) == 1) {
		        
    		    if(left != null) {
    		        level = degree(rootNode.getLeft(), nodeElement);
    		    } else level = null;
    		    
		    }
		    
		    if (rootNode.getValue().compareTo(nodeElement) == -1) {
    		
    		    if (right != null) {
    		        level = degree(rootNode.getRight(), nodeElement);
    		    } else level = null;
		    }
		        return level;
		}
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
	
	//não passou ao tentar remover o raiz 
	@Override
	public boolean remove(Node<T> rootNode, T nodeElement) {
	    Node<T> node = delete(rootNode, nodeElement);
		if(node ==null) return false;
		else
		return true;
	}
	
	 private Node delete(Node<T> rootNode, T nodeElement)
	    {
	        if (rootNode == null)
	            return rootNode;
	  
	        if (nodeElement.compareTo(rootNode.getValue()) == -1)
	            rootNode.setLeft(delete(rootNode.getLeft(), nodeElement));
	        else if (nodeElement.compareTo(rootNode.getValue()) == 1)
	            rootNode.setRight(delete(rootNode.getRight(), nodeElement));
	  
	        else {
	            if (rootNode.getLeft() == null)
	                return rootNode.getRight();
	            else if (rootNode.getRight() == null)
	                return rootNode.getLeft();
	            rootNode.setValue(valorMin(rootNode.getRight()));
	            rootNode.setRight(delete(rootNode.getRight(), rootNode.getValue()));
	        }
	        return rootNode;
	    }
	 
    	private T valorMin(Node<T> root)
    	    {
    	        T minv = root.getValue();
    	        while (root.getLeft()!= null) 
    	        {
    	            minv = root.getLeft().getValue();
    	            root = root.getLeft();
    	        }
    	        return minv;
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
	    if(rootNode == null){
            return null;
        } else if (rootNode.getValue() == element) {
            return rootNode;
        }

        if(rootNode.getValue().compareTo(element) == 1 && rootNode!=null) {
            Node<T> newNode = getByElement(rootNode.getLeft(), element);
            if(newNode != null) return newNode;
        }
        if(rootNode.getValue().compareTo(element) == -1 && rootNode!=null) {
            Node<T> newNode = getByElement(rootNode.getRight(), element);
            if(newNode != null) return newNode;
        }
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
