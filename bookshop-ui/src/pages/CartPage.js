import React, {useEffect, useState} from 'react';
import Bar from "../components/Bar";
import Box from "@mui/material/Box";
import {Grid, Typography} from "@mui/material";
import {RemoteServer} from "../transport/RemoteServer";
import {useParams} from "react-router-dom";
import Button from "@mui/material/Button";
import BookInCart from "../components/BookInCart";

const CartPage = () => {

    const [products, setProducts] = useState([]);
    const { id } = useParams();

    const remoteServer = new RemoteServer();

    const removeAll = () => {
        //TODO zmienić id
        remoteServer
            .removeAllFromProductsCart(1)
            .then(response => {
                if (response.status === 201) {
                    window.location.reload()
                }
            })
    };

    useEffect(() => {
        //TODO zrobic prawdziwe id
        remoteServer
            .getProductsFromCart(1)
            .then(response => {
                if(response.status === 403) {
                    console.log("403")
                }
                else {
                    console.log("wszystko gra")
                }
            })
            .then(async response => {
                console.log(await response.json())
            })
    }, []);

    return (
        <React.Fragment>
            <Bar/>
            <br/>
                <Button
                    style={{ float: 'right', marginRight: '16px'}}
                    variant="contained"
                    size="medium"
                    onClick={removeAll}
                >
                    Wyczyść koszyk
                </Button>
                <br/><br/>
            <br/>
            {products.length !== 0 ?
            <Grid item sm={12}>
                <Box sx={{ width: '100%' }}>
                    <Grid container rowSpacing={1}>
                        {products.map((product) => (
                            <BookInCart key={product.book.id} book = {product.book} amount = {product.amount}/>
                        ))}
                    </Grid>
                </Box>
            </Grid> :
            <Typography variant="h5" component="div" style={{textAlign: 'center'}}>
                Koszyk jest pusty
            </Typography> }
        </React.Fragment>
    );
}

export default CartPage;