package cn.blinkmind.promise.server.repository.entity;

import java.util.LinkedHashSet;

/**
 * @author jiaan.zhang@oracle.com
 * @date 21/10/2016 7:38 PM
 */
public class Node implements Indexable<Long>
{
	private Long id;
	private int ordinal;
	private LinkedHashSet<Node> children = new LinkedHashSet<>();

	public Node(Long id, int ordinal)
	{
		this.id = id;
		this.ordinal = ordinal;
	}

	private Node()
	{

	}

	@Override
	public Long getId()
	{
		return id;
	}

	@Override
	public void setId(Long id)
	{
		this.id = id;
	}

	public int getOrdinal()
	{
		return ordinal;
	}

	public void setOrdinal(int ordinal)
	{
		this.ordinal = ordinal;
	}

	public LinkedHashSet<Node> getChildren()
	{
		return children;
	}

	public void setChildren(LinkedHashSet<Node> children)
	{
		this.children = children;
	}

	public boolean addChild(Node node)
	{
		return this.children.add(node);
	}

	public boolean removeChild(Node node)
	{
		return this.children.remove(node);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Node node = (Node) o;

		return id.equals(node.id);
	}

	@Override
	public int hashCode()
	{
		return id.hashCode();
	}
}
