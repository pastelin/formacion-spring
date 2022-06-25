const obtenerProductos = (producto) => {
    
    const formData = new FormData();
    formData.append('term', producto);
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
            const resultado = await resp.json();
            console.log(resultado);
        }
        console.log(resp);
    } catch (err) {
        throw err;
    }
}

