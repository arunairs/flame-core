package cn.blinkmind.flame.server.util.patch;

public class PatchEvent
{
    public static class UpdateEvent<T>
    {
        public String field;
        public String mappedBy;
        public Object previous;
        public Object current;
        public T converted;

        public UpdateEvent(String field, String mappedBy, Object previous, Object current, T converted)
        {
            this.field = field;
            this.mappedBy = mappedBy;
            this.previous = previous;
            this.current = current;
            this.converted = converted;
        }
    }
}
