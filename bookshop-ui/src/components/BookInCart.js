import React from "react";
import {
    Card,
    CardContent,
    FormControl,
    Grid,
    InputLabel,
    MenuItem,
    Select,
    Typography
} from '@mui/material';
import Button from '@mui/material/Button';
import {RemoteServer} from "../transport/RemoteServer";
import DeleteIcon from '@mui/icons-material/Delete';
import Box from "@mui/material/Box";

const BookInCart = (props) => {
    const {
        book,
        amount
    } = props;

    const [moneyAmount, setAmount] = React.useState(amount);

    const remoteServer = new RemoteServer();

    const handleChange = (event) => {
        setAmount(event.target.value);

        remoteServer
            .replaceProduct(book.id, event.target.value)
            .then()
    };

    const getPrice = (price, amount) => {
        return (price*amount).toFixed(2)
    }

    function removeBook() {
        remoteServer
            .removeProductFromCart( book.id)
            .then(response => {
                if (response.status === 201) {
                    window.location.reload()
                }
            })
    }

    return (
        <React.Fragment>
            <Grid id={'book_cart_' + book.id} item sm={12} style={{marginBottom: '16px'}}>
                <Card sx={{ minWidth: 600 }}>
                    <CardContent>
                        <Box style={{textAlign: "right"}} onClick={removeBook}>
                            <Button>
                                <DeleteIcon id={'delete_from_cart_' + book.id} fontSize="large" style={{color: "black"}}/>
                            </Button>
                        </Box>
                        <Typography id={'book_in_cart_title_' + book.id} variant="h5" component="div">
                            {book.title}
                        </Typography>
                        <Typography id={'book_in_cart_author_' + book.id} sx={{ mb: 1.5 }} color="text.secondary">
                            {book.author}
                        </Typography>
                        <br/>
                        <FormControl fullWidth>
                            <InputLabel id="amount">Ilość</InputLabel>
                            <Select
                                labelId="demo-simple-select-label"
                                id="amount"
                                value={moneyAmount}
                                label="Ilość"
                                onChange={handleChange}
                            >
                                <MenuItem value={1}>1</MenuItem>
                                <MenuItem value={2}>2</MenuItem>
                                <MenuItem value={3}>3</MenuItem>
                                <MenuItem value={4}>4</MenuItem>
                                <MenuItem value={5}>5</MenuItem>
                            </Select>
                        </FormControl>
                        <br/>
                        <br/>
                        <Typography id={'book_in_cart_price_' + book.id} sx={{ mb: 1.5 }} color="text.secondary">
                            {getPrice(book.price, moneyAmount)} zł
                        </Typography>
                    </CardContent>
                </Card>
            </Grid>
        </React.Fragment>
    );
}

export default BookInCart;