package school.cactus.succulentshop


class EmailValidator : Validator {
    override fun validate(field: String) = when {
        field.isEmpty() -> R.string.this_field_is_required
        field.length < 5 -> R.string.identifier_is_too_short
        field.contains("@") -> null
        else -> R.string.enter_an_email_address
    }
}