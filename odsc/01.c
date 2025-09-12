#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_NO_DIGS 350000 + 1

#define TRUE        1
#define FALSE       0

void comp9 (char *of, const size_t length)
{
	for (size_t i = 0; i < length; i++)
		of[i] = ('9' - of[i]) + '0';
}

void add_zeroes (char *source, size_t d0, size_t length)
{
	char new[MAX_NO_DIGS] = {0};

	for (size_t i = 0; i < d0; i++)
	{
		new[i] = '0';
	}
	for (size_t i = 0, j = d0; i < d0 + length; i++)
	{
		new[j++] = source[i];
	}
	memcpy(source, new, d0 + length);
}

unsigned char perform_subtraction(char *dividendo, size_t *length1, char *divisor, size_t length2)
{
	if (*length1 < length2) { add_zeroes(dividendo, length2 - *length1, *length1); *length1 = length2; }
	if (length2 < *length1) { add_zeroes(divisor, *length1 - length2, length2); length2 = *length1; }


	comp9(divisor, length2);

	char temporalBuffer[MAX_NO_DIGS] = {0};
	int sbi = 0;

	unsigned char carry = FALSE;

	for (int i = *length1 - 1; i >= 0; i--)
	{
		int dig1 = dividendo[i] - '0';
		int dig2 = divisor[i] - '0';
		int added = dig1 + dig2 + carry;

		if (added >= 10)
		{
			carry = TRUE;
			temporalBuffer[sbi++] = (added % 10) + '0';
		}
		else
		{
			carry = FALSE;
			temporalBuffer[sbi++] = added + '0';
		}
	}

	if (!carry) { return TRUE; }

	for (int i = sbi - 1, j = 0; i >= 0; i--)
	{
		dividendo[j++] =  temporalBuffer[i];
	}
	*length1 = sbi;

	for (int i = sbi - 1; i >= 0 && carry; i--)
	{
		if (dividendo[i] == '9')
		{
			dividendo[i] = '0';
		}
		else
		{
			dividendo[i]++;
			carry = FALSE;
		}
	}
	return FALSE;
}


void perform_divsion (char *dividendo, char *divisor)
{
	int times = 0;
	size_t length1 = strlen(dividendo), length2 = strlen(divisor);

	char backup[MAX_NO_DIGS] = {0};
	memcpy(backup, divisor, length2);

	while (1)
	{
		if (perform_subtraction(dividendo, &length1, divisor, length2) == TRUE)
		{
			break;
		}
		memcpy(divisor, backup, length2);
		times++;
	}

	printf("%d\n", times);
	int offset = 0;
	while (dividendo[offset] == '0') offset++;
	printf("%s\n", dividendo + offset);
}

int main ()
{
	int no_cases;
	scanf("%d", &no_cases);

	while (no_cases--)
	{
		char dividendo[MAX_NO_DIGS], divisor[MAX_NO_DIGS];

		memset(dividendo, 0, sizeof(dividendo));
		memset(divisor, 0, sizeof(divisor));

		scanf("%s %s", dividendo, divisor);
		perform_divsion(dividendo, divisor);
	}
	return 0;
}
