import React from "react";
import {styled} from "@mui/material/styles";
import Paper from "@mui/material/Paper";
import {Collapse, Grid, IconButton, Typography} from '@mui/material';
import Button from '@mui/material/Button';
import {NavLink, useNavigate} from "react-router-dom";
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import InfoIcon from '@mui/icons-material/Info';
import {RemoteServer} from "../transport/RemoteServer";
import {Alert} from "@mui/lab";
import CloseIcon from '@mui/icons-material/Close';
import Box from "@mui/material/Box";

const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'left',
    color: theme.palette.text.secondary,
}));

const Book = (props) => {
    const {
        book
    } = props;

    const navigate = useNavigate();
    const remoteServer = new RemoteServer();
    const [infoOpen, setInfoOpen] = React.useState(false);

    const sleep = ms => new Promise(
        resolve => setTimeout(resolve, ms)
    );

    function addToCart() {
        remoteServer
            .addProductToCart(book.id)
            .then(async response => {
                if (response.status === 201) {
                    setInfoOpen(true);
                    await sleep(1000);
                    setInfoOpen(false);
                }
                if (response.status === 403) {
                    navigate('/login')
                }
            });
    }

    return (
        <React.Fragment>
            <Box sx={{ width: '100%' }}>
            <Collapse in={infoOpen}>
                <Alert
                    action={
                        <IconButton
                            aria-label="close"
                            color="inherit"
                            size="small"
                            onClick={() => {
                                setInfoOpen(false);
                            }}
                        >
                            <CloseIcon fontSize="inherit" />
                        </IconButton>
                    }
                    sx={{ mb: 2 }}
                >
                    Książka dodana poprawnie!
                </Alert>
            </Collapse>
            </Box>
            <Grid item sm={12}>
                <Item id={"book_" + props.book.id}>
                    <Typography id={'book_panel_title_' + book.id}>{book.title}</Typography>
                        <Button
                            id={'add_to_cart_icon_' + book.id}
                            style={{ float: 'right', marginRight: '16px'}}
                            variant="contained"
                            size="small"
                            onClick={addToCart}
                        >
                            <AddShoppingCartIcon/>
                        </Button>
                    <NavLink
                        to={{pathname:`/book/${book.id}`
                    }}>
                        <Button
                            id={'book_info_icon_' + book.id}
                            style={{ float: 'right', marginRight: '16px'}}
                            variant="contained"
                            size="small"
                        >
                            <InfoIcon/>
                        </Button>
                    </NavLink>
                </Item>
            </Grid>
        </React.Fragment>
    );
}

export default Book;