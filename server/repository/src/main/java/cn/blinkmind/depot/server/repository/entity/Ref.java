package cn.blinkmind.depot.server.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author jiaan.zhang@oracle.com
 * @date 18/10/2016 11:44 AM
 */
@SuppressWarnings("unchecked")
public class Ref<ID>
{
	ID id;

	public ID getId()
	{
		return id;
	}

	@JsonIgnore
	public void setId(ID id)
	{
		this.id = id;
	}

	private Ref()
	{
	}

	public Ref(Indexable indexable)
	{
		this.id = (ID) indexable.getId();
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Ref<?> ref = (Ref<?>) o;

		return id.equals(ref.id);
	}

	@Override
	public int hashCode()
	{
		return id.hashCode();
	}
}
