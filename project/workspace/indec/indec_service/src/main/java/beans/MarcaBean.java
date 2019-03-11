package beans;

import com.google.gson.annotations.SerializedName;

public class MarcaBean implements Bean {

	@SerializedName("marca")
	private String marca;

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "MarcaBean{" +
				"marca='" + marca + '\'' +
				'}';
	}
}
