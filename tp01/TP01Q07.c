#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

bool IsVogal(char str[1000]) {
  for (int i = 0; i < strlen(str); i++) {
    if (!(str[i] == 97 || str[i] == 101 || str[i] == 105 || str[i] == 111 ||
          str[i] == 117)) {
      return false;
    }
  }
  return true;
}
bool IsConsoante(char str[1000]) {
  for (int i = 0; i < strlen(str); i++) {
    if (!((str[i] >= 65 && str[i] <= 90)) || (str[i] >= 97 && str[i] <= 122)) {
      return false;
    }
  }
  return true;
}

bool IsNumerico(char str[1000]) {
  for (int i = 0; i < strlen(str); i++) {
    if (!(str[i] >= 0 && str[i] <= 9)) {
      return false;
    }
  }
  return true;
}
bool IsReal(char str[1000]) {
  for (int i = 0; i < strlen(str); i++) {
    if (str[i] == '.' || str[i] == ',') {
      if (str[i + 1] >= 0 && str[i + 1] <= 9)
        return true;
    }
  }
  return false;
}

int main(void) {
  char entrada[1000];
  scanf(" %[^\n]", entrada);
  while (!(entrada[0] == 'F' && entrada[1] == 'I' && entrada[2] == 'M' &&
           entrada[3] == '\0')) {
    if (IsVogal(entrada))
      printf("SIM ");
    else
      printf("NAO ");
    if (IsConsoante(entrada))
      printf("SIM ");
    else
      printf("NAO ");
    if (IsNumerico(entrada))
      printf("SIM ");
    else
      printf("NAO ");
    if (IsReal(entrada))
      printf("SIM\n");
    else
      printf("NAO\n");
    scanf(" %[^\n]", entrada);
  }
}