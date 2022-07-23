import {CardElement, useElements, useStripe} from "@stripe/react-stripe-js";
import {useState, useEffect} from "react";
import './PaymentForm.css';
import {RemoteServer} from "../transport/RemoteServer";
import {useNavigate} from "react-router-dom";
import {Typography} from "@mui/material";

const PaymentForm = () => {
    const [success, setSuccess] = useState(false)
    const [sum, setSum] = useState(0.0)
    const stripe = useStripe()
    const elements = useElements()
    const navigate = useNavigate()

    const remoteServer = new RemoteServer()

    const removeAll = () => {
        remoteServer
            .removeAllFromProductsCart()
            .then(response => {
                if (response.status === 201) {

                }
            })
    };

    const sendForm = async (values) => {
        remoteServer.pay(values, sum)
            .then(response => {
                    if (response.status === 200) {
                        setSuccess(true)
                        removeAll()
                    } else {
                        alert("Płatność nie powiodła się")
                        navigate('/')
                    }
                }
            )
    }

    useEffect(() => {
        remoteServer
            .getSum()
            .then(async response => {
                    if (response.status === 200) {
                        let result = JSON.parse(await response.text())
                        setSum(result)
                    }
                }
            )
    }, []);

    const handleSubmitForm = async (values) => {
        values.preventDefault()
        const { error, paymentMethod } = await stripe.createPaymentMethod({
            type: "card",
            card: elements.getElement(CardElement)
        })

        if (!error) {
            const { id } = paymentMethod
            sendForm(id)
        }
    }

    return (
        <>
            {!success ?
                <div className="payment">
                    <form id="payment-form" className="formCard"  onSubmit={(values) => { handleSubmitForm(values) }}>
                        <div id="card-element"><CardElement className="inputCard"/></div>
                        <Typography >Do zapłaty: {sum.toFixed(2)} zł </Typography>
                        <button className='buttonCard' id="submit">
                            Zapłać
                        </button>
                    </form>
                </div>
                :
                navigate('/')
            }
        </>
    )
}

export default PaymentForm