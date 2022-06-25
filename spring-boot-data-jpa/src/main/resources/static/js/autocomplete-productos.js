const obtenerProductos = async(producto) => {
    
    const url = `/factura/cargar-productos/${producto}`;
    
    try {
        const resp = await fetch(url, {
            data: {
                term: producto,
            },
            headers: {
                'Content-Type': 'application/json',
            },
        });
    
        if (resp.ok) {

            const data = await resp.json();

            return data.map( item => {
                return {
                    id: item.id,
                    nombre: item.nombre,
                    precio: item.precio
                }
            } );
            
        } else {
            throw await resp.json();
        }
        
    } catch (err) {
        throw err;
    }
}


const buscarProducto = document.querySelector('#buscarProducto');

const eventos = () => {
	buscarProducto.addEventListener('keyup', async () => {
		const producto = buscarProducto.value;

        const productos = await obtenerProductos(producto);

        let linea = document.querySelector('#plantillaItemsFactura');

        linea = linea.replaceChild(/{ID}/g, productos);

        console.log(linea);
	});
};

eventos();
