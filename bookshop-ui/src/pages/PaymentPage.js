import { Elements } from "@stripe/react-stripe-js"
import { loadStripe } from "@stripe/stripe-js"
import PaymentForm from "../components/PaymentForm";

const PaymentPage = () => {
    const publishable_key = 'pk_test_51LONLYFFrkWAItKjWhWeRM4lE3RtpE15agdi1k3owlCFND1qTbsrzYi1INLo0hpAIKLXnDjI72JYFoMCd0c8dsAR00Qi7jKkU5'
    const stripeTestLoad = loadStripe(publishable_key)

    return (
        <Elements stripe={stripeTestLoad}>
            <PaymentForm />
        </Elements>
    )
}

export default PaymentPage