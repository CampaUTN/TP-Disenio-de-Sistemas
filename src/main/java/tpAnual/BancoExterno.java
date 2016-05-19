package tpAnual;

public class BancoExterno
{
    private String[] servicios;

    private String sucursal;

    private String gerente;

    private String banco;

    private String y;

    private String x;

    public String[] getServicios ()
    {
        return servicios;
    }

    public void setServicios (String[] servicios)
    {
        this.servicios = servicios;
    }

    public String getSucursal ()
    {
        return sucursal;
    }

    public void setSucursal (String sucursal)
    {
        this.sucursal = sucursal;
    }

    public String getGerente ()
    {
        return gerente;
    }

    public void setGerente (String gerente)
    {
        this.gerente = gerente;
    }

    public String getBanco ()
    {
        return banco;
    }

    public void setBanco (String banco)
    {
        this.banco = banco;
    }

    public String getY ()
    {
        return y;
    }

    public void setY (String y)
    {
        this.y = y;
    }

    public String getX ()
    {
        return x;
    }

    public void setX (String x)
    {
        this.x = x;
    }

    @Override
    public String toString()
    {
        return "[servicios = "+servicios+", sucursal = "+sucursal+", gerente = "+gerente+", banco = "+banco+", y = "+y+", x = "+x+"]";
    }
}