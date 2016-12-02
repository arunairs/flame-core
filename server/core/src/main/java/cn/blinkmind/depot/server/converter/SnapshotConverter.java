package cn.blinkmind.depot.server.converter;

import cn.blinkmind.depot.server.repository.entity.Snapshot;
import cn.blinkmind.depot.server.repository.entity.View;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;

/**
 * @author jiaan.zhang@oracle.com
 * @date 01/12/2016 5:59 PM
 */
public class SnapshotConverter implements Converter<String, Snapshot>
{
	@Override
	public Snapshot convert(String source)
	{
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.getDeserializationConfig().withView(View.WithBranchId.class);
		return objectMapper.convertValue(source, Snapshot.class);
	}
}
