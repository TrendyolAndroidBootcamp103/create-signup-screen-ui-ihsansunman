package school.cactus.succulentshop


class UsernameValidator : Validator {
    override fun validate(field: String) = when {
        field.isEmpty() -> R.string.this_field_is_required
        field.length < 5 -> R.string.username_is_too_short
        else -> null
    }
}